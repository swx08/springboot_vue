package com.springboot.service.impl;

import com.springboot.entity.Menu;
import com.springboot.mapper.MenuMapper;
import com.springboot.service.IMenuService;
import com.springboot.service.exception.MenunameNotFoundException;
import com.springboot.service.exception.UpdateException;
import com.springboot.service.exception.insertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:RoleServiceImpl
 * Package:com.springboot.service.impl
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 11:15
 * @Version:v1.0
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> getMenu() {
        return menuMapper.getMenu();
    }

    @Override
    public void insert(Menu menu) {
        if(menu.getId() == null){
            Integer rows = menuMapper.insert(menu);
            if(rows != 1){
                throw new insertException("插入时出现未知异常");
            }
        }else {
            Integer rows = menuMapper.update(menu);
            if(rows != 1){
                throw new UpdateException("修改时出现未知异常");
            }
        }
    }
    @Override
    public void delete(Integer id){
        menuMapper.delete(id);
    }

    @Override
    public Integer count(String name) {
        return menuMapper.count(name);
    }

    @Override
    public List<Menu> selectPage(Integer pageNum, Integer pageSize, String name) {
        return menuMapper.selectPage(pageNum,pageSize,name);
    }

    @Override
    public void deleteByBatch(int[] ids) {
        menuMapper.deleteByBatch(ids);
    }

    @Override
    public Menu findById(Integer id) {
        Menu menu = menuMapper.findById(id);
        if(menu == null){
            throw new MenunameNotFoundException("此菜单不存在！");
        }
        return menu;
    }

    @Override
    public Menu findByName(String name) {
        Menu menu = menuMapper.findByName(name);
        if(menu == null){
            throw new MenunameNotFoundException("此菜单不存在！");
        }
        return menu;
    }
}
