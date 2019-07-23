package com.github.eugeneheen.berry.kit.enumeration;

import lombok.Getter;

/**
 * <p>算法枚举，定义了常用的一些加密算法。适用于：DES、3DES、AES、Hex、Base64、MD5</p>
 *
 * @author Eugene
 * 2019-07-19 13:25
 */
@Getter
public enum AlgorithmsEnum {
    /**
     * MD5加密算法
     */
    MD5("md5"),
    /**
     * SHA1加密算法
     */
    SHA1("sha1"),
    /**
     * DES密钥算法
     */
    DES("DES"),
    /**
     * DESede密钥算法
     */
    DESEDE("DESede"),
    /**
     * AES密钥算法
     */
    AES("AES"),
    /**
     * RSA密钥算法
     */
    RSA("RSA"),
    /**
     * DES-Cipher，加密 / 解密算法 / 工作模式 / 填充方式
     */
    DES_CIPHER("DES/ECB/PKCS5Padding");

    /**
     * 算法值
     */
    private String algorithms;

    AlgorithmsEnum(String algorithms) {
        this.algorithms = algorithms;
    }
}
