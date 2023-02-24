package com.springboot.service.exception;

/**
 * ClassName:MenunameNotFoundException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 17:51
 * @Version:v1.0
 */
public class MenunameNotFoundException extends ServiceException{
    public MenunameNotFoundException() {
        super();
    }

    public MenunameNotFoundException(String message) {
        super(message);
    }

    public MenunameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenunameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MenunameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
