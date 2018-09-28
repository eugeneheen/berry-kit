package com.github.eugeneheen.berry.kit.exception;

import java.io.Serializable;

/**
 * 异常基础消息类
 *
 * @author Eugene
 */
public class DefineException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 6626905912600321447L;

    /**
     * 异常错误编码
     */
    private long errorCode;

    /**
     * 异常错误信息
     */
    private String message;

    public DefineException() {
        super();
    }

    public DefineException(String message) {
        super(message);
        this.message = message;
    }

    public DefineException(long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public DefineException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public DefineException(long errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public DefineException(Throwable cause) {
        super(cause);
    }
}
