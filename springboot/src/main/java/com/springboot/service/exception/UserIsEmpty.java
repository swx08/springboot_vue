package com.springboot.service.exception;

/**
 * ClassName:UserIsEmpty
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 17:04
 * @Version:v1.0
 */
public class UserIsEmpty extends ServiceException{
    public UserIsEmpty() {
        super();
    }

    public UserIsEmpty(String message) {
        super(message);
    }

    public UserIsEmpty(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsEmpty(Throwable cause) {
        super(cause);
    }

    protected UserIsEmpty(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
