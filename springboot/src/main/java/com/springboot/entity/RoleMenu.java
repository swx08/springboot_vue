package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ClassName:RoleMenu
 * Package:com.springboot.entity
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 12:58
 * @Version:v1.0
 * 角色和菜单联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
