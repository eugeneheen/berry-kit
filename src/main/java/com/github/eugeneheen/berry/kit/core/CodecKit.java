package com.github.eugeneheen.berry.kit.core;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * <p>
 * 加密解密工具箱，提供了常用的加解密算法实现<br>
 * 由于MD5 与SHA-1均是从MD4 发展而来，它们的结构和强度等特性有很多相似之处，
 * SHA-1与MD5 的最大区别在于其摘要比MD5 摘要长 32 比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。
 * 对于强行攻击，MD5 是2128 数量级的操作，SHA-1 是2160数量级的操作，
 * 对于相同摘要的两个报文的难度：MD5是 264 是数量级的操作，SHA-1 是280 数量级的操作。
 * 因而，SHA-1 对强行攻击的强度更大。 但由于SHA-1 的循环步骤比MD5 多（80:64）且要处理的缓存大（160 比特:128 比特），SHA-1 的运行速度比MD5 慢。
 * </p>
 * @author Eugene
 */
public class CodecKit {

    /**
     * MD5算法
     */
    public static final String ALGORITHMS_MD5 = "md5";

    /**
     * SHA1算法
     */
    public static final String ALGORITHMS_SHA1 = "sha1";

    /**
     * 构造方法
     */
    public CodecKit() {
    }

    /**
     * 通过指定的算法进行16进制编码，如果指定了不支持的算法将返回null值
     *
     * @param bytes      待编码的byte字节数组
     * @param algorithms 编码算法，通过ALGORITHMS_MD5、ALGORITHMS_SHA1等常量指定
     * @return 已编码的16进制字符串
     */
    public String hex(byte[] bytes, String algorithms) {
        switch (algorithms) {
            case ALGORITHMS_SHA1:
                return DigestUtils.sha1Hex(bytes);
            case ALGORITHMS_MD5:
                return DigestUtils.md5Hex(bytes);
            default:
                return null;
        }
    }

    /**
     * 通过指定的算法进行16进制编码，如果指定了不支持的算法将返回null值
     * @param source 需要加密的字符串
     * @param algorithms 编码算法，通过ALGORITHMS_MD5、ALGORITHMS_SHA1等常量指定
     * @return 已编码的16进制字符串
     */
    public String hex(String source, String algorithms) {
        return this.hex(source.getBytes(), algorithms);
    }

    /**
     * Base64编码加密
     * @param bytes 需要加密的字节数组
     * @return Base64加密后的字符串
     */
    public String encodeBase64(byte [] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * Base64编码加密
     * @param text 需要加密的字符串
     * @return Base64加密后的字符串
     */
    public String encodeBase64(String text) {
        return this.encodeBase64(text.getBytes());
    }

    /**
     * Base64编码解密
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public String decodeBase64(byte [] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    /**
     * Base64编码解密
     * @param base64Str 需要解密的Base64字符串
     * @return 加密前的原始字符串
     */
    public String decodeBase64(String base64Str) {
        return this.decodeBase64(base64Str.getBytes());
    }

    /**
     * 解码URL地址
     *
     * @param encodedUrl 已编码的URL地址
     * @param charset    编码字符集
     * @return 已解码的URL
     * @throws DecoderException             解码发生异常
     * @throws UnsupportedEncodingException 不支持的编码字符集
     */
    public String decodeUrl(String encodedUrl, Charset charset) throws DecoderException, UnsupportedEncodingException {
        return new URLCodec().decode(encodedUrl, charset.toString());
    }

    /**
     * 编码URL地址
     *
     * @param url     URL地址
     * @param charset 编码字符集
     * @return 已编码的URL
     * @throws UnsupportedEncodingException 不支持的编码字符集
     */
    public String encodeUrl(String url, Charset charset) throws UnsupportedEncodingException {
        return new URLCodec().encode(url, charset.toString());
    }
}
