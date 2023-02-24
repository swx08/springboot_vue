package com.springboot.mapper;

import com.springboot.entity.Role;

import java.util.List;

/**
 * ClassName:RoleMapper
 * Package:com.springboot.mapper
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 11:12
 * @Version:v1.0
 */
public interface RoleMapper {
    List<Role> getRole();

    Integer insert(Role role);

    Integer update(Role role);

    Integer delete(Integer id);

    Integer count(String name);

    List<Role> selectPage(Integer pageNum,Integer pageSize,String name);

    Integer deleteByBatch(int[] ids);

    Role findById(Integer id);

    Role findByName(String name);
}
