package com.springboot.service;

import com.springboot.entity.RoleMenu;

import java.util.List;

/**
 * ClassName:RoleMenuService
 * Package:com.springboot.service
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 13:16
 * @Version:v1.0
 */
public interface IRoleMenuService {
    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> selectByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
