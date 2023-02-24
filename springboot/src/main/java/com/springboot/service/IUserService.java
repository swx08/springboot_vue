package com.springboot.service;
import com.springboot.controller.DTO.UserDTO;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:IUserService
 * Package:com.springboot.service
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/20 - 14:27
 * @Version:v1.0
 */
public interface IUserService {
    List<User> getUser();

    Integer insert(User user);

    Integer delete(Integer id);

    List<User> selectPage(Integer pageNum,Integer pageSize,String username,String address);

    Integer count(String username,String address);

    Integer deleteByBatch(int[] ids);

    UserDTO findByUserDTO(UserDTO userDTO);

    UserDTO getById(Integer id);

    User findByUserName(String username);

    void insertForRegist(User user);
}
