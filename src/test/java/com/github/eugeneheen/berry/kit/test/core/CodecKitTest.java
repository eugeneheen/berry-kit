package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.CodecKit;
import com.github.eugeneheen.berry.kit.enumeration.DigitsEnum;
import com.github.eugeneheen.berry.kit.enumeration.SecretKeyTypeEnum;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class CodecKitTest {
    private static CodecKit codecKit;

    @BeforeClass
    public static void beforeClass() {
        codecKit = new CodecKit();
    }

    @Test
    public void encodeBase64() {
        //编码
        String encode = codecKit.encodeBase64("Eugene".getBytes());
        Assert.assertTrue(Base64.isBase64(encode));
        encode = codecKit.encodeBase64("Eugene");
        Assert.assertTrue(Base64.isBase64(encode));

        //解码
        String decode = codecKit.decodeBase64(encode.getBytes());
        Assert.assertEquals("Eugene", decode);
        decode = codecKit.decodeBase64(encode);
        Assert.assertEquals("Eugene", decode);
    }

    @Test
    public void testGenSecretKey() {
        final String KEY = "eugenHeen_123456";
        SecretKeySpec keySpec = codecKit.genAesKey(KEY, DigitsEnum.AES_128);
        Assert.assertNotNull(keySpec);
        keySpec = codecKit.genAesKey(KEY, DigitsEnum.AES_128);
        Assert.assertNotNull(keySpec);
    }



    @Test
    public void testDES() {
        final String KEY = "eugenHeen_123456";
        final String META = "Test_DES_EncodeAndDecode";
        String crypyText = codecKit.desEncrypt(KEY, META);
        Assert.assertNotNull(crypyText);
        String metaText = codecKit.desDecrypt(KEY, crypyText);
        Assert.assertEquals(META, metaText);
    }

    @Test
    public void test3DES() {
        String KEY = "eUGeNebeRrYKiTc22";

        String defaultVal = "{\"name\": \"eugene\",\"age\": 18}";
        String encodeDafault = this.codecKit.des3Encrypt(KEY, defaultVal);
        Assert.assertNotNull(encodeDafault);
        String decode3Des = codecKit.des3Decrypt(KEY, encodeDafault);
        Assert.assertEquals(defaultVal, decode3Des);

        defaultVal = "{\"name\":\"测试\",\"userName\":\"code\",\"nickName\":\"CODE\",\"passwrod\":\"\",\"mobileNo\":\"111111111111\",\"gender\":\"1\",\"birthday\":\"2019-07-15T16:00:00.000Z\",\"identityCardNo\":\"111111111111\",\"registerChannel\":-1,\"provinceCode\":\"130000\",\"provinceName\":\"河北省\",\"cityCode\":\"\",\"cityName\":\"\",\"regionCode\":\"\",\"regionName\":\"\",\"creator\":\"13a0cd1c8ec98a6ee7f2d4c1a3a1fbc0\",\"parentId\":\"\",\"password\":\"d41d8cd98f00b204e9800998ecf8427e\"}";
        encodeDafault = codecKit.des3Encrypt(KEY, defaultVal);
        Assert.assertNotNull(encodeDafault);
        decode3Des = codecKit.des3Decrypt(KEY, encodeDafault);
        Assert.assertEquals(defaultVal, decode3Des);
    }

    @Test
    public void testAES() {
        String KEY = "eUGeNebeRrYKiTc22";
        String defaultVal = "{\"name\": \"eugene\",\"age\": 18}";

        String encrypt = codecKit.aesEncrypt(KEY, defaultVal);
        Assert.assertNotNull(encrypt);
        String decrypt = codecKit.aesDecrypt(KEY, encrypt);
        Assert.assertEquals(defaultVal, decrypt);

        encrypt = codecKit.aesEncrypt(KEY, DigitsEnum.AES_192, defaultVal);
        Assert.assertNotNull(encrypt);
        decrypt = codecKit.aesDecrypt(KEY, DigitsEnum.AES_192, encrypt);
        Assert.assertEquals(defaultVal, decrypt);
    }

    @Test
    public void testRSA() {
        // 默认加密因子
        Map<String, RSAKey> rsaKeys = codecKit.genRestoreRsaKeys(DigitsEnum.RSA_4096);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) rsaKeys.get(SecretKeyTypeEnum.PRIVATE_KEY.getType());
        RSAPublicKey rsaPublicKey = (RSAPublicKey) rsaKeys.get(SecretKeyTypeEnum.PUBLIC_KEY.getType());

        Map<String, String> rsaStringKeys = codecKit.genRsaKeys(DigitsEnum.RSA_4096);
        System.out.println("================================");
        System.out.println("私钥：" + rsaStringKeys.get(SecretKeyTypeEnum.PRIVATE_KEY.getType()));
        System.out.println("公钥：" + rsaStringKeys.get(SecretKeyTypeEnum.PUBLIC_KEY.getType()));
        System.out.println("================================");

        final String meta = "这是一个<Good Food>!---------_So Key....两个黄鹂鸣翠柳，一行白鹭上青天。窗含西岭千秋雪，门泊东吴万里船。";
        String privateKeyEncrypt = codecKit.rsaEncrypt(meta, rsaPrivateKey);
        Assert.assertNotNull(privateKeyEncrypt);
        String metaStr = codecKit.rsaDecrypt(privateKeyEncrypt, rsaPublicKey);
        Assert.assertEquals(meta, metaStr);

        privateKeyEncrypt = codecKit.rsaEncrypt(meta, rsaPublicKey);
        Assert.assertNotNull(privateKeyEncrypt);
        metaStr = codecKit.rsaDecrypt(privateKeyEncrypt, rsaPrivateKey);
        Assert.assertEquals(meta, metaStr);

        // 自定义加密因子
        final String SEED = "%$#^*#^%()_(_CXzT8eugeneHeEN4";
        rsaKeys = codecKit.genRestoreRsaKeys(DigitsEnum.RSA_4096, SEED);
        rsaPrivateKey = (RSAPrivateKey) rsaKeys.get(SecretKeyTypeEnum.PRIVATE_KEY.getType());
        rsaPublicKey = (RSAPublicKey) rsaKeys.get(SecretKeyTypeEnum.PUBLIC_KEY.getType());

        rsaStringKeys = codecKit.genRsaKeys(DigitsEnum.RSA_4096, SEED);
        System.out.println("私钥：" + rsaStringKeys.get(SecretKeyTypeEnum.PRIVATE_KEY.getType()));
        System.out.println("公钥：" + rsaStringKeys.get(SecretKeyTypeEnum.PUBLIC_KEY.getType()));
        System.out.println("================================");

        privateKeyEncrypt = codecKit.rsaEncrypt(meta, rsaPrivateKey);
        Assert.assertNotNull(privateKeyEncrypt);
        metaStr = codecKit.rsaDecrypt(privateKeyEncrypt, rsaPublicKey);
        Assert.assertEquals(meta, metaStr);

        privateKeyEncrypt = codecKit.rsaEncrypt(meta, rsaPublicKey);
        Assert.assertNotNull(privateKeyEncrypt);
        metaStr = codecKit.rsaDecrypt(privateKeyEncrypt, rsaPrivateKey);
        Assert.assertEquals(meta, metaStr);
    }

    @Test
    public void testEncodeUrl() {
        final String url = "http://demo.trevet.cn/account/register/SN7KNIPsk845dPkfEwdZ5A==/xxxx";
        try {
            String encodeUrl = CodecKitTest.codecKit.encodeUrl(url);
            String decodeUrl =  CodecKitTest.codecKit.decodeUrl(encodeUrl);
            Assert.assertEquals(url, decodeUrl);
        } catch (DecoderException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
