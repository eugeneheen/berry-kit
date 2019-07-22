package com.github.eugeneheen.berry.kit.enumeration;

import lombok.Getter;

/**
 * <p>加密操作位数枚举定义，定义了DES、3DES、AES支持的加密位数</p>
 *
 * @author Eugene
 * 2019-07-19 13:40
 */
@Getter
public enum DigitsEnum {
    DES_56(56), THREE_DES_112(112), THREE_DES_168(168), AES_128(128), AES_192(192), AES_256(256);

    private int digits;

    DigitsEnum(int digits) {
        this.digits = digits;
    }
}
