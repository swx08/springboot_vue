package com.springboot.service.exception;

/**
 * ClassName:RolenameNotFoundException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 17:49
 * @Version:v1.0
 */
public class RolenameNotFoundException extends ServiceException{
    public RolenameNotFoundException() {
        super();
    }

    public RolenameNotFoundException(String message) {
        super(message);
    }

    public RolenameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RolenameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RolenameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
