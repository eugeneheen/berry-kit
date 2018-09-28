package com.github.eugeneheen.berry.kit.exception.jwt;

import com.github.eugeneheen.berry.kit.exception.SecurityException;

/**
 * JWT Token已过期异常
 * @author Eugene
 */
public class JwtTokenExpiredException extends SecurityException {
    public JwtTokenExpiredException() {
        super();
    }

    public JwtTokenExpiredException(String message) {
        super(message);
    }

    public JwtTokenExpiredException(long errorCode, String message) {
        super(errorCode, message);
    }

    public JwtTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenExpiredException(long errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public JwtTokenExpiredException(Throwable cause) {
        super(cause);
    }
}
