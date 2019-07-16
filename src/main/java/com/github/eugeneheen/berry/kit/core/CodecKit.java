package com.github.eugeneheen.berry.kit.core;

import com.github.eugeneheen.berry.kit.exception.CodecException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
     * 3DES算法
     */
    public static final String ALGORITHMS_DESEDE = "DESede";

    /**
     * AES算法
     */
    public static final String ALGORITHMS_AES = "AES";

    /**
     * AES/ECB/PKCS5Padding算法
     */
    public static final String ALGORITHMS_AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5Padding";

    /**
     * 3DES默认加密Key
     */
    public static final String EDES_DEFAULT_KEY = "eUGeNebeRrYKiTc23zHnjAVa";

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
     * 转换16进制字符串为字节数组
     * @param source 待转换的字符串
     * @param algorithms 编码算法，通过ALGORITHMS_MD5、ALGORITHMS_SHA1等常量指定
     * @return 字节数组
     */
    public byte [] hex2Bytes(String source, String algorithms) {
        byte [] sourceHexBytes = this.hex(source, algorithms).getBytes();
        byte [] clonesourceHexBytes = new byte [24];
        for (int i = 0; i < 24; i++) {
            clonesourceHexBytes[i] = sourceHexBytes[i];
        }
        return clonesourceHexBytes;
    }



    /**
     * Base64编码加密
     * @param bytes 需要加密的字节数组
     * @return Base64加密后的字符串
     */
    public String encodeBase64(byte [] bytes) {
        return Base64.encodeBase64String(bytes);
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
     * @param text 需要加密的字符串
     * @return Base64加密后的字符串
     */
    public byte [] decodeBase64Bytes(String text) {
        return Base64.decodeBase64(text);
    }

    /**
     * Base64编码解密
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public byte [] decodeBase64Bytes(byte [] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * Base64编码解密
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public String decodeBase64(byte [] bytes) {
        return new String(this.decodeBase64Bytes(bytes));
    }

    /**
     * Base64编码解密
     * @param base64Str 需要解密的Base64字符串
     * @return 加密前的原始字符串
     */
    public String decodeBase64(String base64Str) {
        return new String(this.decodeBase64Bytes(base64Str));
    }

    /**
     * 3DES加密
     * @param key 加密Key(自定义)
     * @param srcStr 待加密字符串(24字节)
     * @return 3DES加密后的字符串
     */
    public String encode3Des(String key, String srcStr) {
        try {
            byte [] keyBytes = this.hex2Bytes(key, CodecKit.ALGORITHMS_MD5);
            byte [] srcStrBytes = srcStr.getBytes();
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, CodecKit.ALGORITHMS_DESEDE);
            //加密
            Cipher cipher = Cipher.getInstance(CodecKit.ALGORITHMS_DESEDE);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return this.encodeBase64(cipher.doFinal(srcStrBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("3DES加密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("3DES加密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("3DES加密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("3DES加密发生发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("3DES加密发生发生错误", e);
        }
    }

    /**
     * 3DES-AES加密
     * @param key 加密Key(自定义)
     * @param srcStr 待加密字符串(24字节)
     * @return 3DES加密后的字符串
     */
    public String encode3DesAes(String key, String srcStr) {
        try {
            byte [] keyBytes = key.getBytes();
            byte [] srcStrBytes = srcStr.getBytes();
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, CodecKit.ALGORITHMS_AES);
            //加密
            Cipher cipher = Cipher.getInstance(CodecKit.ALGORITHMS_AES_ECB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return this.encodeBase64(cipher.doFinal(srcStrBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("3DES加密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("3DES加密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("3DES加密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("3DES加密发生发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("3DES加密发生发生错误", e);
        }
    }

    /**
     * 3DES加密(使用默认加密Key)
     * @param srcStr 待加密字符串(24字节)
     * @return 3DES加密后的字符串
     */
    public String encode3Des(String srcStr) {
        return this.encode3Des(CodecKit.EDES_DEFAULT_KEY, srcStr);
    }

    /**
     * 3DES-AES加密(使用默认加密Key)
     * @param srcStr 待加密字符串(24字节)
     * @return 3DES加密后的字符串
     */
    public String encode3DesAes(String srcStr) {
        return this.encode3Des(CodecKit.EDES_DEFAULT_KEY, srcStr);
    }

    /**
     * 3DES解密
     * @param key 加密Key(自定义)
     * @param encodeStr 3DES加密后的字符串
     * @return 原始字符串
     */
    public String decode3Des(String key, String encodeStr) {
        try {
            byte [] keyBytes = this.hex2Bytes(key, CodecKit.ALGORITHMS_MD5);
            byte [] encodeStrBytes = this.decodeBase64Bytes(encodeStr);
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, CodecKit.ALGORITHMS_DESEDE);
            //加密
            Cipher cipher = Cipher.getInstance(CodecKit.ALGORITHMS_DESEDE);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            return new String(cipher.doFinal(encodeStrBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("3DES解密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("3DES解密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("3DES解密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("3DES解密发生发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("3DES解密发生发生错误", e);
        }
    }

    /**
     * 3DES-AES解密
     * @param key 加密Key(自定义)
     * @param encodeStr 3DES加密后的字符串
     * @return 原始字符串
     */
    public String decode3DesAes(String key, String encodeStr) {
        try {
            byte [] keyBytes = key.getBytes();
            byte [] encodeStrBytes = this.decodeBase64Bytes(encodeStr);
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, CodecKit.ALGORITHMS_AES);
            //加密
            Cipher cipher = Cipher.getInstance(CodecKit.ALGORITHMS_AES_ECB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            return new String(cipher.doFinal(encodeStrBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("3DES解密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("3DES解密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("3DES解密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("3DES解密发生发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("3DES解密发生发生错误", e);
        }
    }

    /**
     * 3DES解密(使用默认加密Key)
     * @param encodeStr 3DES加密后的字符串
     * @return 原始字符串
     */
    public String decode3Des(String encodeStr) {
        return this.decode3Des(CodecKit.EDES_DEFAULT_KEY, encodeStr);
    }

    /**
     * 3DES-AES解密(使用默认加密Key)
     * @param encodeStr 3DES加密后的字符串
     * @return 原始字符串
     */
    public String decode3DesAes(String encodeStr) {
        return this.decode3DesAes(CodecKit.EDES_DEFAULT_KEY, encodeStr);
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
     * 解码URL地址，默认使用UTF-8编码
     *
     * @param encodedUrl 已编码的URL地址
     * @return 已解码的URL
     * @throws DecoderException             解码发生异常
     * @throws UnsupportedEncodingException 不支持的编码字符集
     */
    public String decodeUrl(String encodedUrl) throws DecoderException, UnsupportedEncodingException {
        return this.decodeUrl(encodedUrl, StandardCharsets.UTF_8);
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

    /**
     * 编码URL地址，默认使用UTF-8编码
     *
     * @param url     URL地址
     * @return 已编码的URL
     * @throws UnsupportedEncodingException 不支持的编码字符集
     */
    public String encodeUrl(String url) throws UnsupportedEncodingException {
        return this.encodeUrl(url, StandardCharsets.UTF_8);
    }
}
