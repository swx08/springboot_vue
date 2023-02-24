package com.springboot.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.springboot.entity.Role;
import com.springboot.service.IRoleService;
import com.springboot.utils.JWTUtils;
import com.springboot.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:RoleCotroller
 * Package:com.springboot.controller
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 11:17
 * @Version:v1.0
 */
@RestController
@RequestMapping("/Roles")
public class RoleController extends BaseController{
    @Autowired
    private IRoleService roleService;

    /**
     * 查询全部用户数据
     * @return
     */
    @GetMapping
    public ResultType<List<Role>> findAllRole(){
        List<Role> data = roleService.getRole();
        return new ResultType<>(OK,data);
    }

    /**
     * 新增操作
     * @param role
     * @return
     */
    @PostMapping
    public ResultType<Void> insert(@RequestBody Role role){
        roleService.insert(role);
        return new ResultType<>(OK);
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultType<Void> delete(@PathVariable Integer id){
        roleService.delete(id);
        return new ResultType<>(OK);
    }

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    public ResultType<Void> deleteByBatch(@RequestBody int[] ids){
        roleService.deleteByBatch(ids);
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
                                                       @RequestParam String name){
        pageNum = (pageNum - 1) * pageSize;
        //进行模糊查询
        name = "%"+name+"%";
        Integer total = roleService.count(name);
        List<Role> data = roleService.selectPage(pageNum, pageSize,name);
        Map<String,Object> staticData = new HashMap<>();
        staticData.put("data",data);
        staticData.put("total",total);
        //获取当前登录的用户信息
        JWTUtils.getCurrentUser();
        return new ResultType<>(OK,staticData);
    }

    @GetMapping("/{name}")
    public ResultType<Role> getByName(@PathVariable String name){
        Role data = roleService.findByName(name);
        return new ResultType<>(OK,data);
    }
}
