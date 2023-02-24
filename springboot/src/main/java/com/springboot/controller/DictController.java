package com.springboot.controller;

import com.springboot.entity.Dict;
import com.springboot.service.IDictService;
import com.springboot.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName:DictController
 * Package:com.springboot.controller
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 12:01
 * @Version:v1.0
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController{
    @Autowired
    private IDictService dictService;

    @GetMapping
    public ResultType<List<Dict>> getDict(){
        List<Dict> data = dictService.getDictByType(DICT_TYPE_ICON);
        return new ResultType<>(OK,data);
    }
}
