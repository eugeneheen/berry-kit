package com.github.eugeneheen.berry.kit.core;

import com.github.eugeneheen.berry.kit.enumeration.AlgorithmsEnum;
import com.github.eugeneheen.berry.kit.enumeration.DigitsEnum;
import com.github.eugeneheen.berry.kit.enumeration.SecretKeyTypeEnum;
import com.github.eugeneheen.berry.kit.exception.CodecException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 加密解密工具箱，提供了常用的加解密算法实现。<br>
 * 由于MD5 与SHA-1均是从MD4 发展而来，它们的结构和强度等特性有很多相似之处，
 * SHA-1与MD5 的最大区别在于其摘要比MD5 摘要长 32 比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。<br>
 * 对于强行攻击，MD5 是2128 数量级的操作，SHA-1 是2160数量级的操作，
 * 对于相同摘要的两个报文的难度：MD5是 264 是数量级的操作，SHA-1 是280 数量级的操作。
 * 因而，SHA-1 对强行攻击的强度更大。 但由于SHA-1 的循环步骤比MD5 多（80:64）且要处理的缓存大（160 比特:128 比特），SHA-1 的运行速度比MD5 慢。
 * </p>
 *
 * @author Eugene
 */
public class CodecKit {

    /**
     * RSA加密默认种子
     */
    public static final String RSA_DEFAULT_SEED = "$%^*%^()(CXT8eugene4";

    /**
     * 构造方法
     */
    public CodecKit() {
    }

    /**
     * <p>通过指定的算法进行16进制编码，如果指定了不支持的算法将返回null值</p>
     *
     * @param bytes      待编码的byte字节数组
     * @param algorithms 编码算法，支持算法：{@link AlgorithmsEnum#MD5}、{@link AlgorithmsEnum#SHA1}
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
     * <p>通过指定的算法进行16进制编码，如果指定了不支持的算法将返回null值</p>
     *
     * @param source     需要加密的字符串
     * @param algorithms 编码算法，支持算法：{@link AlgorithmsEnum#MD5}、{@link AlgorithmsEnum#SHA1}
     * @return 已编码的16进制字符串
     */
    public String hex(String source, AlgorithmsEnum algorithms) {
        return this.hex(source.getBytes(), algorithms);
    }

    /**
     * <p>转换16进制字符串为字节数组</p>
     *
     * @param source     待转换的字符串
     * @param algorithms 编码算法，支持算法：{@link AlgorithmsEnum#MD5}、{@link AlgorithmsEnum#SHA1}
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
     * <p>Base64编码加密</p>
     *
     * @param bytes 需要加密的字节数组
     * @return Base64加密后的字符串
     */
    public String encodeBase64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * <p>Base64编码加密</p>
     *
     * @param text 需要加密的字符串
     * @return Base64加密后的字符串
     */
    public String encodeBase64(String text) {
        return this.encodeBase64(text.getBytes());
    }

    /**
     * <p>Base64编码解密</p>
     *
     * @param text 需要加密的字符串
     * @return Base64加密后的字符串
     */
    public byte[] decodeBase64Bytes(String text) {
        return Base64.decodeBase64(text);
    }

    /**
     * <p>Base64编码解密</p>
     *
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public byte[] decodeBase64Bytes(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * <p>Base64编码解密</p>
     *
     * @param bytes 需要解密的Base64字节数组
     * @return 加密前的原始字符串
     */
    public String decodeBase64(byte[] bytes) {
        return new String(this.decodeBase64Bytes(bytes));
    }

    /**
     * <p>Base64编码解密</p>
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
     * @param key    密钥
     * @param digits 加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#AES_128}、{@link DigitsEnum#AES_192}、{@link DigitsEnum#AES_256}。获得无政策权限后可使用：{@link DigitsEnum#AES_192}、{@link DigitsEnum#AES_256}
     * @return SecretKeySpec类型的密钥
     * @throws CodecException 抛出CodecKit工具类统一的运行时异常
     * @see NoSuchAlgorithmException 生成密钥使用的算法无法解析
     */
    public SecretKeySpec genAesKey(String key, DigitsEnum digits) {

        try {
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            // 密钥生成器
            KeyGenerator keyGen = KeyGenerator.getInstance(AlgorithmsEnum.AES.getAlgorithms());
            // 初始化密钥生成器
            keyGen.init(digits.getDigits(), new SecureRandom(keyBytes));
            // 生成密钥
            SecretKey secretKey = keyGen.generateKey();
            // 密钥字节数组
            byte[] keyByts = secretKey.getEncoded();
            return new SecretKeySpec(keyByts, AlgorithmsEnum.AES.getAlgorithms());
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("生成AES密钥，算法无法解析", e);
        }
    }

    /**
     * <p>还原RSA密钥</p>
     * <p>RSA密钥对中存放的公钥或私钥，还原为{@link RSAPrivateKey}私钥或{@link RSAPublicKey}公钥。</p>
     *
     * @param rsaKey        RSA密钥对中获取的公钥（获取Key：{@link SecretKeyTypeEnum#PUBLIC_KEY}）或私钥（获取Key：{@link SecretKeyTypeEnum#PRIVATE_KEY}）。生成密钥对的方式使用：{@link CodecKit#genRsaKeys(DigitsEnum)}、{@link CodecKit#genRsaKeys(DigitsEnum, String)}
     * @param secretKeyType 转换结果密钥类型，指定转换结果为私钥：{@link SecretKeyTypeEnum#PRIVATE_KEY}；指定转换结果为公钥：{@link SecretKeyTypeEnum#PUBLIC_KEY}
     * @return {@link RSAPrivateKey}私钥或{@link RSAPublicKey}公钥
     * @throws CodecException 密钥类型无法解析、算法无法解析、无效的密钥将抛出异常
     */
    public RSAKey restoreRsaKey(String rsaKey, SecretKeyTypeEnum secretKeyType) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmsEnum.RSA.getAlgorithms());
            switch (secretKeyType) {
                case PRIVATE_KEY:
                    // 通过PKCS#8编码的Key指令获得私钥对象
                    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(this.decodeBase64Bytes(rsaKey));
                    return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
                case PUBLIC_KEY:
                    // 通过X509编码的Key指令获得公钥对象
                    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(this.decodeBase64Bytes(rsaKey));
                    return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
                default:
                    throw new CodecException("转换RSA密钥，密钥类型无法解析");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("转换RSA密钥，算法无法解析", e);
        } catch (InvalidKeySpecException e) {
            throw new CodecException("转换RSA密钥，使用的Key无效", e);
        }
    }

    /**
     * <p>生成RSA密钥对，可通过{@link CodecKit#restoreRsaKey(String, SecretKeyTypeEnum)}还原已生成的密钥对</p>
     *
     * @param digits 加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#RSA_2048}、{@link DigitsEnum#RSA_3072}、{@link DigitsEnum#RSA_4096}
     * @param seed   加密种子，用户自定义。种子可包含特殊字符
     * @return RSA密匙对：Map&lt;String, RSAKey&gt;，获取公钥使用Key：{@link SecretKeyTypeEnum#PUBLIC_KEY}；获取私钥使用Key：{@link SecretKeyTypeEnum#PRIVATE_KEY}
     */
    public Map<String, String> genRsaKeys(DigitsEnum digits, String seed) {
        try {
            //  RSA算法创建一个KeyPairGenerator对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(AlgorithmsEnum.RSA.getAlgorithms());
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(seed.getBytes(StandardCharsets.UTF_8));
            keyPairGenerator.initialize(digits.getDigits(), secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            Key privateKey = keyPair.getPrivate();
            String privateStringKey = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
            Key publicKey = keyPair.getPublic();
            String publicStringKey = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
            Map<String, String> keyPairMap = new HashMap<>(2);
            keyPairMap.put(SecretKeyTypeEnum.PRIVATE_KEY.getType(), privateStringKey);
            keyPairMap.put(SecretKeyTypeEnum.PUBLIC_KEY.getType(), publicStringKey);
            return keyPairMap;
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("生成RSA密钥，算法无法解析", e);
        }
    }

    /**
     * <p>生成RSA密钥对，生成密匙对时使用默认加密种子{@link CodecKit#RSA_DEFAULT_SEED}。可通过{@link CodecKit#restoreRsaKey(String, SecretKeyTypeEnum)}还原已生成的密钥对</p>
     *
     * @param digits 加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#RSA_2048}、{@link DigitsEnum#RSA_3072}、{@link DigitsEnum#RSA_4096}
     * @return 包含公钥私钥的一个：Map&lt;String, String&gt;，获取公钥的Key：{@link SecretKeyTypeEnum#PUBLIC_KEY}；获取私钥的Key：{@link SecretKeyTypeEnum#PRIVATE_KEY}
     */
    public Map<String, String> genRsaKeys(DigitsEnum digits) {
        return this.genRsaKeys(digits, CodecKit.RSA_DEFAULT_SEED);
    }

    /**
     * <p>生成已还原的RSA密钥对</p>
     *
     * @param digits 加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#RSA_2048}、{@link DigitsEnum#RSA_3072}、{@link DigitsEnum#RSA_4096}
     * @param seed   加密种子，用户自定义。种子可包含特殊字符
     * @return 包含已还原公钥私钥的一个：Map&lt;String, RSAKey&gt;，获取公钥的Key：{@link SecretKeyTypeEnum#PUBLIC_KEY}；获取私钥的Key：{@link SecretKeyTypeEnum#PRIVATE_KEY}
     */
    public Map<String, RSAKey> genRestoreRsaKeys(DigitsEnum digits, String seed) {
        Map<String, String> keys = this.genRsaKeys(digits, seed);
        String privateStringKey = keys.get(SecretKeyTypeEnum.PRIVATE_KEY.getType());
        String publicStringKey = keys.get(SecretKeyTypeEnum.PUBLIC_KEY.getType());

        // 通过PKCS#8编码的Key指令还原RSA私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) this.restoreRsaKey(privateStringKey, SecretKeyTypeEnum.PRIVATE_KEY);
        // 通过X509编码的Key指令还原RSA公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) this.restoreRsaKey(publicStringKey, SecretKeyTypeEnum.PUBLIC_KEY);

        Map<String, RSAKey> keyPairMap = new HashMap<>(2);
        keyPairMap.put(SecretKeyTypeEnum.PRIVATE_KEY.getType(), rsaPrivateKey);
        keyPairMap.put(SecretKeyTypeEnum.PUBLIC_KEY.getType(), rsaPublicKey);
        return keyPairMap;
    }

    /**
     * <p>生成已还原的RSA密钥对，生成密匙对时使用默认加密种子{@link CodecKit#RSA_DEFAULT_SEED}。</p>
     *
     * @param digits 加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#RSA_2048}、{@link DigitsEnum#RSA_3072}、{@link DigitsEnum#RSA_4096}
     * @return 包含已还原公钥私钥的一个：Map&lt;String, String&gt;，获取公钥的Key：{{@link SecretKeyTypeEnum#PUBLIC_KEY}；获取私钥的Key：{@link SecretKeyTypeEnum#PRIVATE_KEY}
     */
    public Map<String, RSAKey> genRestoreRsaKeys(DigitsEnum digits) {
        return this.genRestoreRsaKeys(digits, CodecKit.RSA_DEFAULT_SEED);
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
     * @param mode 加密模式:{@link Cipher#ENCRYPT_MODE}；解密模式：{@link Cipher#DECRYPT_MODE}
     * @param key  密钥
     * @param text 加解密数据
     * @return 加密解密后的数据
     */
    public byte[] desEncryptOrDecrypt(int mode, byte[] key, byte[] text) {
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
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.DES_CIPHER.getAlgorithms());
            // 用密匙初始化Cipher对象
            cipher.init(mode, secretKey, random);
            // 获取数据并加密，正式执行加密操作
            return cipher.doFinal(text);
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
     * <p>3DES加密</p>
     *
     * @param key    密钥
     * @param srcStr 待加密字符串
     * @return 3DES加密后的字符串
     */
    public String des3Encrypt(String key, String srcStr) {
        byte[] keyBytes = this.hex2Bytes(key, AlgorithmsEnum.MD5);
        byte[] srcStrBytes = srcStr.getBytes();
        byte[] resultByts = this.des3EncryptOrDecrypt(Cipher.ENCRYPT_MODE, keyBytes, srcStrBytes);
        return this.encodeBase64(resultByts);
    }

    /**
     * <p>3DES解密</p>
     *
     * @param key       密钥
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
     * @param mode 加密模式:{@link Cipher#ENCRYPT_MODE}；解密模式：{@link Cipher#DECRYPT_MODE}
     * @param key  密钥
     * @param text 加解密数据
     * @return 加密解密后的数据
     */
    public byte[] des3EncryptOrDecrypt(int mode, byte[] key, byte[] text) {
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
            return cipher.doFinal(text);
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
     * <p>AES加密，默认128位长度的密钥</p>
     *
     * @param key  密钥
     * @param text 待加密的原始内容
     * @return AES加密后的内容
     */
    public String aesEncrypt(String key, String text) {
        SecretKeySpec keySpec = this.genAesKey(key, DigitsEnum.AES_128);
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] encryptBytes = this.aesEncryptOrDecrypt(Cipher.ENCRYPT_MODE, keySpec, textBytes);
        return this.encodeBase64(encryptBytes);
    }

    /**
     * <p>AES加密，使用指定长度的密钥。如果使用默认密钥长度{@link DigitsEnum#AES_128}，直接使用方法{@link CodecKit#aesEncrypt(String, String)}</p>
     *
     * @param key    密钥
     * @param digits 加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#AES_128}、{@link DigitsEnum#AES_192}、{@link DigitsEnum#AES_256}。获得无政策权限后可使用：{@link DigitsEnum#AES_192}、{@link DigitsEnum#AES_256}
     * @param text   待加密的原始内容
     * @return AES加密后的内容
     */
    public String aesEncrypt(String key, DigitsEnum digits, String text) {
        SecretKeySpec keySpec = this.genAesKey(key, digits);
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] encryptBytes = this.aesEncryptOrDecrypt(Cipher.ENCRYPT_MODE, keySpec, textBytes);
        return this.encodeBase64(encryptBytes);
    }

    /**
     * <p>AES解密，默认128位长度的密钥</p>
     *
     * @param key       密钥
     * @param cryptText AES加密后的字符串
     * @return AES加密前的原始内容
     */
    public String aesDecrypt(String key, String cryptText) {
        SecretKeySpec keySpec = this.genAesKey(key, DigitsEnum.AES_128);
        byte[] textBytes = this.decodeBase64Bytes(cryptText);
        byte[] decryptBytes = this.aesEncryptOrDecrypt(Cipher.DECRYPT_MODE, keySpec, textBytes);
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    /**
     * <p>AES解密，使用指定长度的密钥。如果使用默认密钥长度{@link DigitsEnum#AES_128}，直接使用方法{@link CodecKit#aesEncrypt(String, String)}</p>
     *
     * @param key       密钥
     * @param digits    加密密钥长度，支持的加密密钥长度：{@link DigitsEnum#AES_128}、{@link DigitsEnum#AES_192}、{@link DigitsEnum#AES_256}。获得无政策权限后可使用：{@link DigitsEnum#AES_192}、{@link DigitsEnum#AES_256}
     * @param cryptText AES加密后的字符串
     * @return AES加密前的原始内容
     */
    public String aesDecrypt(String key, DigitsEnum digits, String cryptText) {
        SecretKeySpec keySpec = this.genAesKey(key, digits);
        byte[] textBytes = this.decodeBase64Bytes(cryptText);
        byte[] decryptBytes = this.aesEncryptOrDecrypt(Cipher.DECRYPT_MODE, keySpec, textBytes);
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    /**
     * <p>AES加解密</p>
     *
     * @param mode 加密模式:{@link Cipher#ENCRYPT_MODE}；解密模式：{@link Cipher#DECRYPT_MODE}
     * @param key  {@link SecretKeySpec}密钥，使用{@link CodecKit#genAesKey(String, DigitsEnum)}快速创建密钥
     * @param text 加解密数据
     * @return 加密解密后的数据
     */
    public byte[] aesEncryptOrDecrypt(int mode, SecretKeySpec key, byte[] text) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.AES.getAlgorithms());
            cipher.init(mode, key);
            return cipher.doFinal(text);
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
     * <p>RSA加密</p>
     *
     * @param text 待加密的原始内容
     * @param key  RSA密匙，RSA私钥：{@link RSAPrivateKey}、RSA公钥：{@link RSAPublicKey}
     * @return RSA加密字符串
     */
    public String rsaEncrypt(String text, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.RSA.getAlgorithms());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            int keySize = key instanceof RSAPrivateKey ? ((RSAPrivateKey) key).getModulus().bitLength() : ((RSAPublicKey) key).getModulus().bitLength();
            byte[] splitCodecBytes = this.rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, text.getBytes(StandardCharsets.UTF_8), keySize);
            return Base64.encodeBase64URLSafeString(splitCodecBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("RSA私钥加密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("RSA私钥加密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("RSA私钥加密Key无效", e);
        }
    }

    /**
     * <p>RSA解密</p>
     * <p>RSA加密算法依赖一对公钥和私钥，加密与解密是相互对应的，模式如下：</p>
     * <ul>
     * <li>{@link RSAPublicKey}公钥加密，需要使用{@link RSAPrivateKey}私钥解密。</li>
     * <li>{@link RSAPrivateKey}私钥加密，需要使用{@link RSAPublicKey}公钥解密。</li>
     * </ul>
     *
     * @param cryptText RSA加密后的字符串
     * @param key       RSA密匙，RSA私钥：{@link RSAPrivateKey}、RSA公钥：{@link RSAPublicKey}
     * @return 原始字符串
     */
    public String rsaDecrypt(String cryptText, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsEnum.RSA.getAlgorithms());
            cipher.init(Cipher.DECRYPT_MODE, key);
            int keySize = key instanceof RSAPrivateKey ? ((RSAPrivateKey) key).getModulus().bitLength() : ((RSAPublicKey) key).getModulus().bitLength();
            byte[] splitCodecBytes = this.rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(cryptText), keySize);
            return new String(splitCodecBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new CodecException("RSA公钥解密发生使用不支持的编码算法发生错误", e);
        } catch (NoSuchPaddingException e) {
            throw new CodecException("RSA公钥解密发生错误", e);
        } catch (InvalidKeyException e) {
            throw new CodecException("RSA公钥解密Key无效", e);
        }
    }

    /**
     * <p>解码URL地址</p>
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
     * <p>解码URL地址，默认使用UTF-8编码</p>
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
     * <p>编码URL地址</p>
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
     * <p>编码URL地址，默认使用UTF-8编码</p>
     *
     * @param url URL地址
     * @return 已编码的URL
     * @throws UnsupportedEncodingException 不支持的编码字符集
     */
    public String encodeUrl(String url) throws UnsupportedEncodingException {
        return this.encodeUrl(url, StandardCharsets.UTF_8);
    }

    /**
     * RSA分割编解码器
     *
     * @param cipher  {@link Cipher}加密对象
     * @param opmode  加密模式，支持模式：{@link Cipher#ENCRYPT_MODE}、{@link Cipher#DECRYPT_MODE}
     * @param datas   加密数据
     * @param keySize 密钥长度
     * @return 分割后的byte[]字节数组
     */
    private byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }

        int offSet = 0;
        byte[] buff;
        int i = 0;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            return out.toByteArray();
        } catch (BadPaddingException e) {
            throw new CodecException("RSA加解密发生错误", e);
        } catch (IllegalBlockSizeException e) {
            throw new CodecException("RSA加解密发生错误", e);
        } catch (IOException e) {
            throw new CodecException("RSA加解密发生错误", e);
        }
    }

}
