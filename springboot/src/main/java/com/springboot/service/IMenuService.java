package com.springboot.service;

import com.springboot.entity.Menu;
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
public interface IMenuService {
    List<Menu> getMenu();

    void insert(Menu menu);

    void delete(Integer id);

    Integer count(String name);

    List<Menu> selectPage(Integer pageNum,Integer pageSize,String name);

    void deleteByBatch(int[] ids);

    Menu findById(Integer id);

    Menu findByName(String name);
}
