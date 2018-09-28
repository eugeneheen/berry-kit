package com.github.eugeneheen.berry.kit.exception;

/**
 * 不被支持的日志级别异常
 * @author Eugene
 */
public class NoSupportLogLevel extends RuntimeException {

    public NoSupportLogLevel() {
        super();
    }

    public NoSupportLogLevel(String message) {
        super(message);
    }

    public NoSupportLogLevel(String message, Throwable cause) {
        super(message, cause);
    }
}
