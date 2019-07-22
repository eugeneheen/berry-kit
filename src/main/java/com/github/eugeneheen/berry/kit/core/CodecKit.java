package com.github.eugeneheen.berry.kit.core;

import com.github.eugeneheen.berry.kit.enumeration.AlgorithmsEnum;
import com.github.eugeneheen.berry.kit.enumeration.DigitsEnum;
import com.github.eugeneheen.berry.kit.exception.CodecException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * <p>
 * 加密解密工具箱，提供了常用的加解密算法实现<br>
 * 由于MD5 与SHA-1均是从MD4 发展而来，它们的结构和强度等特性有很多相似之处，
 * SHA-1与MD5 的最大区别在于其摘要比MD5 摘要长 32 比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。
 * 对于强行攻击，MD5 是2128 数量级的操作，SHA-1 是2160数量级的操作，
 * 对于相同摘要的两个报文的难度：MD5是 264 是数量级的操作，SHA-1 是280 数量级的操作。
 * 因而，SHA-1 对强行攻击的强度更大。 但由于SHA-1 的循环步骤比MD5 多（80:64）且要处理的缓存大（160 比特:128 比特），SHA-1 的运行速度比MD5 慢。
 * </p>
 *
 * @author Eugene
 */
public class CodecKit {

    /**
     * 构造方法
     */
    public CodecKit() {
    }

    /**
     * 通过指定的算法进行16进制编码，如果指定了不支持的算法将返回null值
     *
     * @param bytes      待编码的byte字节数组
     * @param algorithms 编码算法，详见：{@link AlgorithmsEnum}，支持MD5、SHA1
     * @return 已编码的16进制字符串
     */
    public String hex(byte[] bytes, AlgorithmsEnum algorithms) {
        switch (algorithms) {
            case SHA1:
                return DigestUtils.sha1Hex(bytes);
            case MD5:
                return DigestUtils.md5Hex(bytes);
            default:
                return null;
        }
    }

    /**
     * 通过指定的算法进行16进制编码，如果指定了不支持的算法将返回null值
     *
     * @param source     需要加密的字符串
     * @param algorithms 编码算法，详见：{@link AlgorithmsEnum}，支持MD5、SHA1
     * @return 已编码的16进制字符串
     */
    public String hex(String source, AlgorithmsEnum algorithms) {
        return this.hex(source.getBytes(), algorithms);
    }

    /**
     * 转换16进制字符串为字节数组
     *
     * @param source     待转换的字符串
     * @param algorithms 编码算法，通过ALGORITHMS_MD5、ALGORITHMS_SHA1等常量指定
     * @return 字节数组
     */
    public byte[] hex2Bytes(String source, AlgorithmsEnum algorithms) {
        byte[] sourceHexBytes = this.hex(source, algorithms).getBytes();
        byte[] clonesourceHexBytes = new byte[24];
        for (int i = 0; i < 24; i++) {
            clonesourceHexBytes[i] = sourceHexBytes[i];
        }
        return clonesourceHexBytes;
    }


    /**
     * Base64编码加密
     *
     * @param bytes 需要加密的字节数组
     * @return Base64加密后的字符串
     */
    public String encodeBase64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * Base64编码加密
     *
     * @param text 需要加密的字符串
     * @return Base64加密后的字符串
     */
    public String encodeBase64(String text) {
        return this.encodeBase64(text.getBytes());
    }

    /**
     * Base64编码解密
     *
     * @param text 需要加密的字符串
     * @return Base64加密后的字符串
     */
    public byte[] decodeBase64Bytes(String text) {
        return Base64.decodeBase64(text);
    }

    /**
     * Base64编码解密
     *
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public byte[] decodeBase64Bytes(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * Base64编码解密
     *
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public String decodeBase64(byte[] bytes) {
        return new String(this.decodeBase64Bytes(bytes));
    }

    /**
     * Base64编码解密
     *
     * @param base64Str 需要解密的Base64字符串
     * @return 加密前的原始字符串
     */
    public String decodeBase64(String base64Str) {
        return new String(this.decodeBase64Bytes(base64Str));
    }

    /**
     * <p>生成SecretKeySpec类型AES密钥</p>
     *
     * @param key 密钥
     * @param digits     加密位数，详见：{@link DigitsEnum}
     * @return SecretKeySpec类型的密钥
     * @throws CodecException 抛出CodecKit工具类统一的运行时异常
     * @see NoSuchAlgorithmException 生成密钥使用的算法无法解析
     */
    public SecretKeySpec genAesKey(String key, DigitsEnum digits) {

        try {
            byte [] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            // 密钥生成器
            KeyGenerator keyGen = KeyGenerator.getInstance(AlgorithmsEnum.AES.getAlgorithms());
            // 初始化密钥生成器
            keyGen.init(digits.getDigits(), new SecureRandom(keyBytes));
            // 生成密钥
            SecretKey secretKey = keyGen.generateKey();
            // 密钥字节数组
            byte [] keyByts = secretKey.getEncoded();
            return new SecretKeySpec(keyByts, AlgorithmsEnum.AES.getAlgorithms());
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("生成AES密钥，算法无法解析", e);
        }
    }

    /**
     * <p>DES加密</p>
     *
     * @param key  密钥，长度不得低于8位
     * @param text 待加密的文本
     * @return 加密文本
     */
    public String desEncrypt(String key, String text) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] srcBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] resultBytes = this.desEncryptOrDecrypt(Cipher.ENCRYPT_MODE, keyBytes, srcBytes);
        // encodeBase64会对字符串3位一组自动补全，因而最后可能会出现 == 或者 =
        return this.encodeBase64(resultBytes);
    }

    /**
     * <p>DES解密密</p>
     *
     * @param key       密钥，长度不得低于8位
     * @param cryptText 加密文本
     * @return 解密后的原文本
     */
    public String desDecrypt(String key, String cryptText) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] srcBytes = this.decodeBase64Bytes(cryptText);
        byte[] resultBytes = this.desEncryptOrDecrypt(Cipher.DECRYPT_MODE, keyBytes, srcBytes);
        return new String(resultBytes, StandardCharsets.UTF_8);
    }

    /**
     * <p>DES加解密</p>
     *
     * @param mode 加解密模式，详见:{@link Cipher}；加密模式：ENCRYPT_MODE；解密模式：DECRYPT_MODE
     * @param key  加解密密钥
     * @param data 加解密数据
     * @return 加密解密后的数据
     */
    public byte[] desEncryptOrDecrypt(int mode, byte[] key, byte[] data) {
        try {
            // 随机数生成器（RNG）
            SecureRandom random = new SecureRandom();
            // DESKeySpec是一个成加密密钥的密钥内容的（透明）规范的接口
            DESKeySpec desKeySpec = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(AlgorithmsEnum.DES.getAlgorithms());
            // 得到密钥对象
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.DES.getAlgorithms());
            // 用密匙初始化Cipher对象
            cipher.init(mode, secretKey, random);
            // 获取数据并加密，正式执行加密操作
            return cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            throw new CodecException("DES加解密Key无效", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("DES加解密发生使用不支持的编码算法发生错误", e);
        } catch (InvalidKeySpecException e) {
            throw new CodecException("DES加解密Key无效", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("DES加解密发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("DES加解密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("DES加解密发生错误", e);
        }
    }

    /**
     * 3DES加密
     *
     * @param key    加密Key(自定义)
     * @param srcStr 待加密字符串(24字节)
     * @return 3DES加密后的字符串
     */
    public String des3Encrypt(String key, String srcStr) {
        byte[] keyBytes = this.hex2Bytes(key, AlgorithmsEnum.MD5);
        byte[] srcStrBytes = srcStr.getBytes();
        byte[] resultByts = this.des3EncryptOrDecrypt(Cipher.ENCRYPT_MODE, keyBytes, srcStrBytes);
        return this.encodeBase64(resultByts);
    }

    /**
     * 3DES解密
     *
     * @param key       加密Key(自定义)
     * @param cryptText 3DES加密后的字符串
     * @return 原始字符串
     */
    public String des3Decrypt(String key, String cryptText) {
        byte[] keyBytes = this.hex2Bytes(key, AlgorithmsEnum.MD5);
        byte[] encodeBytes = this.decodeBase64Bytes(cryptText);
        byte[] resultByts = this.des3EncryptOrDecrypt(Cipher.DECRYPT_MODE, keyBytes, encodeBytes);
        return new String(resultByts, StandardCharsets.UTF_8);
    }

    /**
     * <p>3DES加解密</p>
     *
     * @param mode 加解密模式，详见:{@link Cipher}；加密模式：ENCRYPT_MODE；解密模式：DECRYPT_MODE
     * @param key  加解密密钥
     * @param data 加解密数据
     * @return 加密解密后的数据
     */
    public byte[] des3EncryptOrDecrypt(int mode, byte[] key, byte[] data) {
        try {
            // 随机数生成器（RNG）
            SecureRandom random = new SecureRandom();
            // DESedeKeySpec是一个成加密密钥的密钥内容的（透明）规范的接口
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESedeKeySpec转换成
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(AlgorithmsEnum.DESEDE.getAlgorithms());
            // 得到密钥对象
            SecretKey secretKey = secretKeyFactory.generateSecret(deSedeKeySpec);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.DESEDE.getAlgorithms());
            // 用密匙初始化Cipher对象
            cipher.init(mode, secretKey, random);
            // 获取数据并加密，正式执行加密操作
            return cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            throw new CodecException("3DES加解密Key无效", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("3DES加解密发生使用不支持的编码算法发生错误", e);
        } catch (InvalidKeySpecException e) {
            throw new CodecException("3DES加解密Key无效", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("3DES加解密发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("3DES加解密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("3DES加解密发生错误", e);
        }
    }

    /**
     * AES加密，默认128位长度的密钥
     *
     * @param key 密钥
     * @param text 待加密的原始内容
     * @return AES加密后的内容
     */
    public String aesEncrypt(String key, String text) {
        SecretKeySpec keySpec = this.genAesKey(key, DigitsEnum.AES_128);
        byte [] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte [] encryptBytes = this.aesEncryptOrDecrypt(Cipher.ENCRYPT_MODE, keySpec, textBytes);
        return this.encodeBase64(encryptBytes);
    }

    /**
     * AES加密，使用指定长度的密钥
     *
     * @param key 密钥
     * @param digits 密钥长度，获得无政策权限后可设置为192或256的长度
     * @param text 待加密的原始内容
     * @return AES加密后的内容
     */
    public String aesEncrypt(String key, DigitsEnum digits, String text) {
        SecretKeySpec keySpec = this.genAesKey(key, digits);
        byte [] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte [] encryptBytes = this.aesEncryptOrDecrypt(Cipher.ENCRYPT_MODE, keySpec, textBytes);
        return this.encodeBase64(encryptBytes);
    }

    /**
     * AES解密，默认128位长度的密钥
     *
     * @param key 密钥
     * @param cryptText AES加密后的字符串
     * @return AES加密前的原始内容
     */
    public String aesDecrypt(String key, String cryptText) {
        SecretKeySpec keySpec = this.genAesKey(key, DigitsEnum.AES_128);
        byte [] textBytes = this.decodeBase64Bytes(cryptText);
        byte [] decryptBytes = this.aesEncryptOrDecrypt(Cipher.DECRYPT_MODE, keySpec, textBytes);
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    /**
     * AES解密，使用指定长度的密钥
     *
     * @param key 密钥
     * @param digits 密钥长度，获得无政策权限后可设置为192或256的长度
     * @param cryptText AES加密后的字符串
     * @return AES加密前的原始内容
     */
    public String aesDecrypt(String key, DigitsEnum digits, String cryptText) {
        SecretKeySpec keySpec = this.genAesKey(key, digits);
        byte [] textBytes = this.decodeBase64Bytes(cryptText);
        byte [] decryptBytes = this.aesEncryptOrDecrypt(Cipher.DECRYPT_MODE, keySpec, textBytes);
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    /**
     * <p>AES加解密</p>
     *
     * @param mode 加解密模式，详见:{@link Cipher}；加密模式：ENCRYPT_MODE；解密模式：DECRYPT_MODE
     * @param key  加解密密钥
     * @param data 加解密数据
     * @return 加密解密后的数据
     */
    public byte [] aesEncryptOrDecrypt(int mode, SecretKeySpec key, byte [] data) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.AES.getAlgorithms());
            cipher.init(mode, key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("AES加解密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("AES加解密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("AES加解密Key无效", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("AES加解密发生错误", e);
        } catch (BadPaddingException e) {
            throw new CodecException("AES加解密发生错误", e);
        }
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
     * @param url URL地址
     * @return 已编码的URL
     * @throws UnsupportedEncodingException 不支持的编码字符集
     */
    public String encodeUrl(String url) throws UnsupportedEncodingException {
        return this.encodeUrl(url, StandardCharsets.UTF_8);
    }
}
