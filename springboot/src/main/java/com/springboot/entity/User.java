package com.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:User
 * Package:com.springboot.entity
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/20 - 14:00
 * @Version:v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private Date createdTime;
}
