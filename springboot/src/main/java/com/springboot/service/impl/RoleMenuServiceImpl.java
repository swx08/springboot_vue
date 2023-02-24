package com.springboot.service.impl;

import com.springboot.entity.RoleMenu;
import com.springboot.mapper.RoleMenuMapper;
import com.springboot.service.IRoleMenuService;
import com.springboot.service.exception.deleteException;
import com.springboot.service.exception.insertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:RoleMenuServiceImpl
 * Package:com.springboot.service.impl
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 13:16
 * @Version:v1.0
 */
@Service
public class RoleMenuServiceImpl implements IRoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Transactional//事务注解
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        //根据roleId先删除有关的菜单
        roleMenuMapper.deleteByRoleId(roleId);
        //把前端菜单数据id数组绑定到当前的这个角色id上去
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            Integer row = roleMenuMapper.insert(roleMenu);
            if(row != 1){
                throw new insertException("插入时出现未知异常");
            }
        }
    }

    @Override
    public List<Integer> selectByRoleId(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleMenuMapper.deleteByRoleId(roleId);
    }
}
