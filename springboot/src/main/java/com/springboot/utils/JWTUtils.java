package com.springboot.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.springboot.controller.DTO.UserDTO;
import com.springboot.entity.User;
import com.springboot.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * ClassName:JWTUtils
 * Package:com.springboot.utils
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/30 - 13:02
 * @Version:v1.0
 * token使用
 */
@Component
public class JWTUtils {
    private static IUserService staticUserService;

    @Resource
    private IUserService userService;

    @PostConstruct
    public void setStaticUserService(){
        staticUserService = userService;
    }

    /**
     * 生成Token
     * @param userId
     * @param password
     * @return
     */
    public static String genToken(String userId,String password){
        return JWT.create().withAudience(userId) //将userId保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2小时后token过期
                .sign(Algorithm.HMAC256(password));
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public static UserDTO getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(!StrUtil.isBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
