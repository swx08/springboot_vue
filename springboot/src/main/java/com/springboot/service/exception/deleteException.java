package com.springboot.service.exception;

/**
 * ClassName:deleteException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/2/2 - 13:21
 * @Version:v1.0
 */
public class deleteException extends ServiceException{
    public deleteException() {
        super();
    }

    public deleteException(String message) {
        super(message);
    }

    public deleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public deleteException(Throwable cause) {
        super(cause);
    }

    protected deleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
