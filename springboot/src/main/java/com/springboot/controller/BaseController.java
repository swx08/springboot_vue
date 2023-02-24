package com.springboot.controller;

import com.springboot.service.exception.*;
import com.springboot.utils.ResultType;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.KeySelector;

/**
 * ClassName:BaseController
 * Package:com.springboot.controller
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/28 - 12:19
 * @Version:v1.0
 * 基础控制层
 */
public class BaseController {
    public static final String DICT_TYPE_ICON = "icon";
    //成功状态码
    public static final Integer OK = 200;

    //请求处理方法:这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法(全局异常配置)
    @ExceptionHandler(ServiceException.class)
    public ResultType<Void> HandlerException(Throwable e) {
        ResultType<Void> resultType = new ResultType<>(e);
        if (e instanceof UsernameNotFoundException) {
            resultType.setStatue(4000);
            resultType.setMessage("密码错误");
        } else if (e instanceof TokenIsEmptyException) {
            resultType.setStatue(5000);
            resultType.setMessage("无token,请重新登录!");
        } else if (e instanceof TokenVerifyFeildException) {
            resultType.setStatue(5001);
            resultType.setMessage("token验证失败,请重新登录!");
        }else if (e instanceof insertException) {
            resultType.setStatue(4001);
            resultType.setMessage("插入时出现未知异常");
        }else if (e instanceof UpdateException) {
            resultType.setStatue(4002);
            resultType.setMessage("修改时出现未知异常");
        }else if (e instanceof deleteException) {
            resultType.setStatue(4003);
            resultType.setMessage("删除时出现未知异常");
        }else if (e instanceof RolenameNotFoundException) {
            resultType.setStatue(6000);
            resultType.setMessage("此角色不存在");
        }else if (e instanceof RolenameNotFoundException) {
            resultType.setStatue(6001);
            resultType.setMessage("此菜单不存在");
        }else if (e instanceof RolenameNotFoundException) {
            resultType.setStatue(4004);
            resultType.setMessage("用户名不存在,请注册！");
        }
        return resultType;
    }
}
