package com.springboot.config;

import com.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LoginInterceptorConfig
 * Package:com.springboot.config
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/30 - 14:55
 * @Version:v1.0
 */

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所谓白名单就是用户没有登录就可以访问的路径
        //黑名单就是用户没有登录就不可以访问的路径
        //1.创建自定义的拦截器对象
        //2.配置白名单并存放在一个List集合
        List<String> patters = new ArrayList<>();
        patters.add("/users/login");
        patters.add("/**/export");
        patters.add("/**/import");
        patters.add("/file/**");
        patters.add("/users/regist");

        //registry.addInterceptor(interceptor);完成拦截
        // 器的注册,后面的addPathPatterns表示拦截哪些url
        //这里的参数/**表示所有请求,再后面的excludePathPatterns表
        // 示有哪些是白名单,且参数是列表
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patters);
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}
