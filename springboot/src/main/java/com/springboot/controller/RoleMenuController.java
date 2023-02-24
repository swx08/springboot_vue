package com.springboot.controller;

import com.springboot.service.IRoleMenuService;
import com.springboot.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:RoleMenuController
 * Package:com.springboot.controller
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 13:28
 * @Version:v1.0
 */
@RestController
@RequestMapping ("/roleMenu")
public class RoleMenuController extends BaseController{
    @Autowired
    private IRoleMenuService roleMenuService;

    @PostMapping("/{roleId}")
    public ResultType<Void> setRoleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds){
        roleMenuService.setRoleMenu(roleId,menuIds);
        return new ResultType<>(OK);
    }

    @GetMapping("/{roleId}")
    public ResultType<List<Integer>> selectByRoleId(@PathVariable Integer roleId){
        List<Integer> data = roleMenuService.selectByRoleId(roleId);
        return new ResultType<>(OK,data);
    }

    @DeleteMapping("/{roleId}")
    public ResultType<Void> delete(@PathVariable Integer roleId){
        roleMenuService.deleteByRoleId(roleId);
        return new ResultType<>(OK);
    }
}
