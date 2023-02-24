package com.springboot.service.exception;

/**
 * ClassName:loginException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/28 - 12:40
 * @Version:v1.0
 */
public class UsernameNotFoundException extends ServiceException{
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
