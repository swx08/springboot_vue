package com.springboot.mapper;
import com.springboot.controller.DTO.UserDTO;
import com.springboot.entity.User;

import java.util.List;

/**
 * ClassName:UserMapper
 * Package:com.springboot.mapper
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/20 - 13:59
 * @Version:v1.0
 */
public interface UserMapper{

    List<User> getUser();

    Integer insert(User user);

    Integer update(User user);

    Integer delete(Integer id);

    Integer count(String username,String address);

    List<User> selectPage(Integer pageNum,Integer pageSize,String username,String address);

    Integer deleteByBatch(int[] ids);

    UserDTO findByUserDTO(UserDTO userDTO);

    User findByUserName(String username);

    UserDTO getById(Integer id);

    Integer insertForRegist(User user);
}
