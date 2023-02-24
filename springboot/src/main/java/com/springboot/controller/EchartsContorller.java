package com.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.springboot.entity.User;
import com.springboot.service.IUserService;
import com.springboot.utils.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.Sun;

import java.util.*;

/**
 * ClassName:EchartsContorller
 * Package:com.springboot.controller
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/31 - 13:52
 * @Version:v1.0
 * 主页数据报表
 */
@RestController
@RequestMapping("/echarts")
public class EchartsContorller extends BaseController{
    @Autowired
    private IUserService userService;

    @GetMapping("/members")
    public ResultType<List<Integer>> members(){
        List<User> list = userService.getUser();
        int q1 = 0;//第一季度
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User user : list) {
            Date createdTime = user.getCreatedTime();
            Quarter quarter = DateUtil.quarterEnum(createdTime);
            switch (quarter){
                case Q1: q1++; break;
                case Q2: q2++; break;
                case Q3: q3++; break;
                case Q4: q4++; break;
                default: break;
            }
        }
        return new ResultType<>(OK,CollUtil.newArrayList(q1,q2,q3,q4));
    }
}
