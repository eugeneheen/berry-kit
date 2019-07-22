package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.CodecKit;
import com.github.eugeneheen.berry.kit.enumeration.DigitsEnum;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

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
