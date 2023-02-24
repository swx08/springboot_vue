package com.springboot.service.exception;

/**
 * ClassName:insertException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/1 - 10:05
 * @Version:v1.0
 */
public class insertException extends ServiceException{
    public insertException() {
        super();
    }

    public insertException(String message) {
        super(message);
    }

    public insertException(String message, Throwable cause) {
        super(message, cause);
    }

    public insertException(Throwable cause) {
        super(cause);
    }

    protected insertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
