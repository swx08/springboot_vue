package com.springboot.mapper;

import com.springboot.entity.Menu;
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
public interface MenuMapper {
    List<Menu> getMenu();

    Integer insert(Menu menu);

    Integer update(Menu menu);

    Integer delete(Integer id);

    Integer count(String name);

    List<Menu> selectPage(Integer pageNum,Integer pageSize,String name);

    Integer deleteByBatch(int[] ids);

    Menu findById(Integer id);

    Menu findByName(String name);
}
