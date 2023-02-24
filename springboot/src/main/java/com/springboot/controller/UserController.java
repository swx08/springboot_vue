package com.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.protobuf.BoolValueOrBuilder;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.springboot.controller.DTO.UserDTO;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IUserService;
import com.springboot.utils.JWTUtils;
import com.springboot.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:IUserController
 * Package:com.springboot.controller
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/20 - 13:35
 * @Version:v1.0
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 查询全部用户数据
     * @return
     */
    @GetMapping
    public ResultType<List<User>> findAllUser(){
        List<User> data = userService.getUser();
        return new ResultType<>(OK,data);
    }

    /**
     * 新增操作
     * @param user
     * @return
     */
    @PostMapping
    public ResultType<Void> insert(@RequestBody User user){
        userService.insert(user);
        return new ResultType<>(OK);
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultType<Void> delete(@PathVariable Integer id){
        userService.delete(id);
        return new ResultType<>(OK);
    }

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    public ResultType<Void> deleteByBatch(@RequestBody int[] ids){
        userService.deleteByBatch(ids);
        return new ResultType<>(OK);
    }

    /**
     * 分页查询全部用户数据加模糊查询
     * @return
     * /users/page?pageNum=1&pageSize=2
     */
    @GetMapping("/page")
    public ResultType<Map<String,Object>> findPageUser(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam String username,
                                           @RequestParam String address){
        pageNum = (pageNum - 1) * pageSize;
        //进行模糊查询
        username = "%"+username+"%";
        address = "%"+address+"%";
        Integer total = userService.count(username,address);
        List<User> data = userService.selectPage(pageNum, pageSize,username,address);
        Map<String,Object> staticData = new HashMap<>();
        staticData.put("data",data);
        staticData.put("total",total);
        //获取当前登录的用户信息
        JWTUtils.getCurrentUser();
        return new ResultType<>(OK,staticData);
    }

    /**
     * Excel导出功能
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public ResultType<Void> export(HttpServletResponse response) throws IOException {
        //从数据库查询出所有的数据
        List<User> list = userService.getUser();
        //通过工具类创建writer写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUpLoadPath+"/用户信息.xlsx");
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //自定义标题别名

        /*writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");*/

        //一次性写出list内的对象到Excel,使用默认样式，强制输出标题
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
        return new ResultType<>(OK);
    }

    /**
     * Excel导入功能
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    public ResultType<Void> Import(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //通过javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<User> lists = reader.readAll(User.class);

        //忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<User> lists = CollUtil.newArrayList();
//        for(List<Object> row : list){
//            User user = new User();
//            user.setUsername(row.get(0).toString());
//            user.setPassword(row.get(1).toString());
//            user.setNickname(row.get(2).toString());
//            user.setEmail(row.get(3).toString());
//            user.setPhone(row.get(4).toString());
//            user.setAddress(row.get(5).toString());
//            lists.add(user);
//        }
        //因为没有写批量新增方法，所以这里将list里面的数据遍历然后调用insert方法进行新增
        for (User users : lists) {
            userService.insert(users);
        }
        return new ResultType<>(OK);
    }

    @PostMapping("/login")
    public ResultType<UserDTO> login(@RequestBody UserDTO userDTO){
        UserDTO data = userService.findByUserDTO(userDTO);
        return new ResultType<>(OK,data);
    }

    @GetMapping("/{username}")
    public ResultType<User> getByUsername(@PathVariable String username){
        User data = userService.findByUserName(username);
        return new ResultType<>(OK,data);
    }

    @PostMapping("/regist")
    public ResultType<Void> regist(@RequestBody User user){
        userService.insertForRegist(user);
        return new ResultType<>(OK);
    }
}
