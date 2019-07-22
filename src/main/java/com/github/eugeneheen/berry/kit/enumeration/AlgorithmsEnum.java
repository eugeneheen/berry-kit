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
    MD5("md5"), SHA1("sha1"), DES("DES"), DESEDE("DESede"), AES("AES"),
    ALGORITHMS_AES_ECB_PKCS5PADDING("AES/ECB/PKCS5Padding");

    private String algorithms;

    AlgorithmsEnum(String algorithms) {
        this.algorithms = algorithms;
    }
}
