package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName:Role
 * Package:com.springboot.entity
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/31 - 15:45
 * @Version:v1.0
 * 角色实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String description;
}
