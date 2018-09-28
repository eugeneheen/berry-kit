package com.github.eugeneheen.berry.kit.exception.jwt;

import com.github.eugeneheen.berry.kit.exception.SecurityException;

/**
 * JWT Token不合法异常
 * @author Eugene
 */
public class JwtTokenInvalidException extends SecurityException {
    public JwtTokenInvalidException() {
        super();
    }

    public JwtTokenInvalidException(String message) {
        super(message);
    }

    public JwtTokenInvalidException(long errorCode, String message) {
        super(errorCode, message);
    }

    public JwtTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenInvalidException(long errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public JwtTokenInvalidException(Throwable cause) {
        super(cause);
    }
}
