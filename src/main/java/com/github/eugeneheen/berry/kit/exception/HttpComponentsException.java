package com.github.eugeneheen.berry.kit.exception;

/**
 * Apache HttpComponents工具箱操作发生异常<br>
 * @author Eugene
 */
public class HttpComponentsException extends RuntimeException {
    public HttpComponentsException() {
        super();
    }

    public HttpComponentsException(String message) {
        super(message);
    }

    public HttpComponentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
