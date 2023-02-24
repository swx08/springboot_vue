package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * ClassName:Menu
 * Package:com.springboot.entity
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 16:41
 * @Version:v1.0
 * 菜单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu implements Serializable {
    private Integer id;
    private String name;
    private String path;
    private String icon;
    private String description;
    //子菜单
    private transient List<Menu> children;
    private Integer pid;
}
