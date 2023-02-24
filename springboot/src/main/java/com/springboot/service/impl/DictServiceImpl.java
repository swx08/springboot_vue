package com.springboot.service.impl;

import com.springboot.entity.Dict;
import com.springboot.mapper.DictMapper;
import com.springboot.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

/**
 * ClassName:DictService
 * Package:com.springboot.service.impl
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 11:57
 * @Version:v1.0
 */
@Service
public class DictServiceImpl implements IDictService {
    @Autowired
    private DictMapper dictMapper;
    @Override
    public List<Dict> getDictByType(String type) {
        return dictMapper.getDictByType(type);
    }
}
