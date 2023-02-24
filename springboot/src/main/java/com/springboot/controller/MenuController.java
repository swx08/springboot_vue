package com.springboot.controller;

import com.springboot.entity.Menu;
import com.springboot.entity.Role;
import com.springboot.service.IMenuService;
import com.springboot.service.IRoleService;
import com.springboot.utils.JWTUtils;
import com.springboot.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequestMapping("/menus")
public class MenuController extends BaseController{
    @Autowired
    private IMenuService menuService;

    /**
     * 查询全部用户数据
     * @return
     */
    @GetMapping
    public ResultType<List<Menu>> findAllRole(@RequestParam(defaultValue = "") String name){
        List<Menu> list = menuService.getMenu();
        //找出pid为null的一级菜单(java新特性，正则)
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //找出一级菜单的子菜单
        for (Menu menu : parentNode) {
            //筛选所有数据中pid=父级id的数据就是二级菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return new ResultType<>(OK,parentNode);
    }

    /**
     * 新增操作
     * @param menu
     * @return
     */
    @PostMapping
    public ResultType<Void> insert(@RequestBody Menu menu){
        menuService.insert(menu);
        return new ResultType<>(OK);
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultType<Void> delete(@PathVariable Integer id){
        menuService.delete(id);
        return new ResultType<>(OK);
    }

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    public ResultType<Void> deleteByBatch(@RequestBody int[] ids){
        menuService.deleteByBatch(ids);
        return new ResultType<>(OK);
    }

    @GetMapping("/{name}")
    public ResultType<Menu> getByName(@PathVariable String name){
        Menu data = menuService.findByName(name);
        return new ResultType<>(OK,data);
    }
}
