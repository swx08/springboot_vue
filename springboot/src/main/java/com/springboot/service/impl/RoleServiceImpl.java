package com.springboot.service.impl;

import com.springboot.entity.Role;
import com.springboot.mapper.RoleMapper;
import com.springboot.service.IRoleService;
import com.springboot.service.exception.RolenameNotFoundException;
import com.springboot.service.exception.UpdateException;
import com.springboot.service.exception.UsernameNotFoundException;
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
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRole() {
        return roleMapper.getRole();
    }

    @Override
    public void insert(Role role) {
        if(role.getId() == null){
            Integer rows = roleMapper.insert(role);
            if(rows != 1){
                throw new insertException("插入时出现未知异常");
            }
        }else {
            Integer rows = roleMapper.update(role);
            if(rows != 1){
                throw new UpdateException("修改时出现未知异常");
            }
        }
    }

    @Override
    public void delete(Integer id) {
        roleMapper.delete(id);
    }

    @Override
    public List<Role> selectPage(Integer pageNum, Integer pageSize, String name) {
        return roleMapper.selectPage(pageNum,pageSize,name);
    }

    @Override
    public Integer count(String name) {
        return roleMapper.count(name);
    }

    @Override
    public void deleteByBatch(int[] ids) {
        roleMapper.deleteByBatch(ids);
    }

    @Override
    public Role findById(Integer id) {
        Role role = roleMapper.findById(id);
        if(role == null){
            throw new RolenameNotFoundException("此角色不存在！");
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role = roleMapper.findByName(name);
        if(role == null){
            throw new RolenameNotFoundException("此角色不存在！");
        }
        return role;
    }
}
