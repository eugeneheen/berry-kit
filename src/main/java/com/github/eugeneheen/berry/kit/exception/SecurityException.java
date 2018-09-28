package com.github.eugeneheen.berry.kit.exception;


/**
 * 安全异常
 * @author Eugene
 */
public class SecurityException extends DefineException {

	public SecurityException() {
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(long errorCode, String message) {
		super(errorCode, message);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(long errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}
}
