package com.github.eugeneheen.berry.kit.exception;

/**
 * 编码处理异常
 * @author Eugene
 * 2018-09-30 13:12
 */
public class CodecException extends DefineException {
    public CodecException() {
        super();
    }

    public CodecException(String message) {
        super(message);
    }

    public CodecException(long errorCode, String message) {
        super(errorCode, message);
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodecException(long errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public CodecException(Throwable cause) {
        super(cause);
    }
}
