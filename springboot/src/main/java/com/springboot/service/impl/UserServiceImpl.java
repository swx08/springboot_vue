package com.springboot.service.impl;

import cn.hutool.extra.tokenizer.TokenizerUtil;
import com.springboot.controller.DTO.UserDTO;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IUserService;
import com.springboot.service.exception.UpdateException;
import com.springboot.service.exception.UserIsEmpty;
import com.springboot.service.exception.UsernameNotFoundException;
import com.springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ClassName:UserServiceImpl
 * Package:com.springboot.service.impl
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/23 - 22:41
 * @Version:v1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser() {
        return userMapper.getUser();
    }

    @Override
    public Integer insert(User user) {
        if(user.getId() == null){
            return userMapper.insert(user);
        }else {
            return userMapper.update(user);
        }
    }

    @Override
    public Integer delete(Integer id) {
       return userMapper.delete(id);
    }

    @Override
    public List<User> selectPage(Integer pageNum, Integer pageSize,String username,String adderess) {
        return userMapper.selectPage(pageNum,pageSize,username,adderess);
    }

    @Override
    public Integer count(String username, String address) {
        return userMapper.count(username,address);
    }

    @Override
    public Integer deleteByBatch(int[] ids) {
        return userMapper.deleteByBatch(ids);
    }

    @Override
    public UserDTO findByUserDTO(UserDTO userDTO) {
        User userName = userMapper.findByUserName(userDTO.getUsername());
        if(userName == null){
            throw new UserIsEmpty("用户名不存在,请注册！");
        }
        UserDTO user = userMapper.findByUserDTO(userDTO);
        if(user == null){
            throw  new UsernameNotFoundException("密码错误");
        }
        //设置token
        String token = JWTUtils.genToken(user.getId().toString(), user.getPassword());
        user.setToken(token);
        return user;
    }

    @Override
    public UserDTO getById(Integer id) {
        UserDTO userDTO = userMapper.getById(id);
        if(userDTO == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return userDTO;
    }

    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return user;
    }

    @Override
    public void insertForRegist(User user) {
        userMapper.insertForRegist(user);
    }
}
