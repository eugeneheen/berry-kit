package com.github.eugeneheen.berry.kit.exception;

/**
 * 随机数异常<br>
 * @author Eugene
 */
public class RandomException extends RuntimeException {
    public RandomException() {
        super();
    }

    public RandomException(String message) {
        super(message);
    }

    public RandomException(String message, Throwable cause) {
        super(message, cause);
    }
}
