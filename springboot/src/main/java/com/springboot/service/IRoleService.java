package com.springboot.service;

import com.springboot.entity.Role;

import java.util.List;

/**
 * ClassName:IRoleService
 * Package:com.springboot.service
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 11:15
 * @Version:v1.0
 */
public interface IRoleService {
    List<Role> getRole();

    void insert(Role role);

    void delete(Integer id);

    List<Role> selectPage(Integer pageNum,Integer pageSize,String name);

    Integer count(String name);

    void deleteByBatch(int[] ids);

    Role findById(Integer id);

    Role findByName(String name);
}
