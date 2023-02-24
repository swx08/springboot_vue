package com.springboot.service.exception;

/**
 * ClassName:TokenIsEmptyException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/30 - 13:40
 * @Version:v1.0
 */
public class TokenIsEmptyException extends ServiceException{
    public TokenIsEmptyException() {
        super();
    }

    public TokenIsEmptyException(String message) {
        super(message);
    }

    public TokenIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected TokenIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
