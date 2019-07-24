package com.github.eugeneheen.berry.kit.enumeration;

import lombok.Getter;

/**
 * <p>密钥类型枚举定义</p>
 *
 * @author Eugene
 * 2019-07-23 14:07
 */
@Getter
public enum SecretKeyTypeEnum {
    /**
     * 私钥
     */
    PRIVATE_KEY("privateKey"),
    /**
     * 公钥
     */
    PUBLIC_KEY("publicKey");

    /**
     * 密钥类型
     */
    private String type;

    SecretKeyTypeEnum(String type) {
        this.type = type;
    }
}
