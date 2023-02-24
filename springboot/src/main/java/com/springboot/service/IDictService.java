package com.springboot.service;

import com.springboot.entity.Dict;

import java.util.List;

/**
 * ClassName:DictService
 * Package:com.springboot.service
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 11:57
 * @Version:v1.0
 */
public interface IDictService {
    List<Dict> getDictByType(String type);
}
