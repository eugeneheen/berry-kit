package com.github.eugeneheen.berry.kit.exception;

/**
 * Apache HttpResponse工具箱操作发生异常<br>
 * @author Eugene
 */
public class HttpResponseException extends RuntimeException {
    public HttpResponseException() {
        super();
    }

    public HttpResponseException(String message) {
        super(message);
    }

    public HttpResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
