package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName:Dict
 * Package:com.springboot.entity
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 11:55
 * @Version:v1.0
 * 图标实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dict implements Serializable {
    private String name;
    private String value;
    private String type;
}
