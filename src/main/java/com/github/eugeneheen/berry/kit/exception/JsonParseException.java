package com.github.eugeneheen.berry.kit.exception;

/**
 * Json解析异常，当Json解析遇到Json字符串格式错误、对象转换错误等问题时抛出此异常<br>
 * @author Eugene
 */
public class JsonParseException extends RuntimeException {

    public JsonParseException() {
        super();
    }

    public JsonParseException(String message) {
        super(message);
    }

    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
