package com.springboot.service.exception;

/**
 * ClassName:TokenVerifyFeildException
 * Package:com.springboot.service.exception
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/30 - 13:42
 * @Version:v1.0
 * Token验证失败异常
 */
public class TokenVerifyFeildException extends ServiceException{
    public TokenVerifyFeildException() {
        super();
    }

    public TokenVerifyFeildException(String message) {
        super(message);
    }

    public TokenVerifyFeildException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenVerifyFeildException(Throwable cause) {
        super(cause);
    }

    protected TokenVerifyFeildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
