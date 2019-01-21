package com.github.eugeneheen.berry.kit.exception;

/**
 * 不支持的日期字段异常
 *
 * @author Eugene
 * 2019-01-21 13:33
 */
public class NotSupportDateFieldException extends DefineException {
    public NotSupportDateFieldException() {
        super();
    }

    public NotSupportDateFieldException(String message) {
        super(message);
    }

    public NotSupportDateFieldException(long errorCode, String message) {
        super(errorCode, message);
    }

    public NotSupportDateFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportDateFieldException(long errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public NotSupportDateFieldException(Throwable cause) {
        super(cause);
    }
}
