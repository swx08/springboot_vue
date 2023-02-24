package com.springboot.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName:UserDTO
 * Package:com.springboot.controller.DTO
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/28 - 12:10
 * @Version:v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
}
