package com.springboot.mapper;

import com.springboot.entity.Dict;

import java.util.List;

/**
 * ClassName:Dict
 * Package:com.springboot.mapper
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 11:56
 * @Version:v1.0
 */
public interface DictMapper {
    List<Dict> getDictByType(String type);
}
