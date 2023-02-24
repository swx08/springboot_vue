package com.springboot.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.springboot.controller.DTO.UserDTO;
import com.springboot.entity.User;
import com.springboot.service.IUserService;
import com.springboot.service.exception.TokenIsEmptyException;
import com.springboot.service.exception.TokenVerifyFeildException;
import com.springboot.service.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:LoginInterceptor
 * Package:com.springboot.interceptor
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/30 - 13:25
 * @Version:v1.0
 * 设置登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求头里面获取token
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new TokenIsEmptyException("无token,请重新登录!");
        }
        //获取 token中的userId
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            //根据token中的userId查询数据库

        }catch (JWTDecodeException j) {
            throw new TokenVerifyFeildException("token验证失败,请重新登录!");
        }
        UserDTO user = userService.getById(Integer.valueOf(userId));
        if(user == null){
            throw new UsernameNotFoundException("用户不存在，请重新登录!");
        }
        //用户密码加签验证:token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);//验证token
        }catch (JWTVerificationException e){
            throw new TokenVerifyFeildException("token验证失败,请重新登录!");
        }
        return true;
    }
}
