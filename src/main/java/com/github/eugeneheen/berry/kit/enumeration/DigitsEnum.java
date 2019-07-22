package com.github.eugeneheen.berry.kit.enumeration;

import lombok.Getter;

/**
 * <p>加密密钥长度枚举定义，定义了DES、3DES、AES支持的加密密钥长度</p>
 *
 * @author Eugene
 * 2019-07-19 13:40
 */
@Getter
public enum DigitsEnum {
    /**
     * DES加密，56长度密钥
     */
    DES_56(56),
    /**
     * 3DES加密，112长度密钥
     */
    THREE_DES_112(112),
    /**
     * 3DES加密，168长度密钥
     */
    THREE_DES_168(168),
    /**
     * AES加密，128长度密钥
     */
    AES_128(128),
    /**
     * AES加密，192长度密钥
     */
    AES_192(192),
    /**
     * AES加密，256长度密钥
     */
    AES_256(256);

    /**
     * 加密密钥长度值
     */
    private int digits;

    DigitsEnum(int digits) {
        this.digits = digits;
    }
}
