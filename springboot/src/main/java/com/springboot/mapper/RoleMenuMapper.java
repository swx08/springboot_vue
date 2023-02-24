package com.springboot.mapper;

import com.springboot.entity.RoleMenu;

import java.util.List;

/**
 * ClassName:RoleMenu
 * Package:com.springboot.mapper
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 13:10
 * @Version:v1.0
 */
public interface RoleMenuMapper {
    Integer deleteByRoleId(Integer roleId);

    Integer insert(RoleMenu roleMenu);
    List<Integer> selectByRoleId(Integer roleId);
}
