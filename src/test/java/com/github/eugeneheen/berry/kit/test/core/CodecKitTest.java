package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.CodecKit;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void test3Des() {
//        String key = "eUGeNebeRrYKiTc23zHnjAVa0323MKzM";
////        String idCard = "100015";
//        String idCard = "100021";
//        String encode = CodecKitTest.codecKit.encode3Des(key);
////        Assert.assertEquals(idCard, CodecKitTest.codecKit.decode3Des(key, encode));
//        System.out.println(encode);

        String defaultVal = "{\"name\": \"eugene\",\"age\": 18}";
        String encodeDafault = this.codecKit.encode3Des("avc", defaultVal);
        System.out.println(encodeDafault);
        String decode3Des = this.codecKit.decode3Des("avc", encodeDafault);
        System.out.println(decode3Des);


        encodeDafault = this.codecKit.encode3DesAes("abcdefgabcdefg12", "{\"name\":\"测试\",\"userName\":\"code\",\"nickName\":\"CODE\",\"passwrod\":\"\",\"mobileNo\":\"111111111111\",\"gender\":\"1\",\"birthday\":\"2019-07-15T16:00:00.000Z\",\"identityCardNo\":\"111111111111\",\"registerChannel\":-1,\"provinceCode\":\"130000\",\"provinceName\":\"河北省\",\"cityCode\":\"\",\"cityName\":\"\",\"regionCode\":\"\",\"regionName\":\"\",\"creator\":\"13a0cd1c8ec98a6ee7f2d4c1a3a1fbc0\",\"parentId\":\"\",\"password\":\"d41d8cd98f00b204e9800998ecf8427e\"}");
        System.out.println(encodeDafault);
        decode3Des = this.codecKit.decode3DesAes("abcdefgabcdefg12", encodeDafault);
        System.out.println(decode3Des);
//        Assert.assertEquals(defaultVal, this.codecKit.decode3Des(encodeDafault));
    }

    @Test
    public void testEncodeUrl() {
//        try {
//            String encodeUrl = CodecKitTest.codecKit.encodeUrl("http://demo.trevet.cn/account/register/SN7KNIPsk845dPkfEwdZ5A==/xxxx");
//            System.out.println(encodeUrl);
//            String decodeUrl = CodecKitTest.codecKit.decodeUrl(encodeUrl);
//            System.out.println(decodeUrl);
//            System.out.println("081jFcl90MpkPw1H0El90R9wl90jFclk".length());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (DecoderException e) {
//            e.printStackTrace();
//        }
        try {
            String url =  CodecKitTest.codecKit.decodeUrl("http%3A%2F%2Fwx.toyou.net%2Frouter%2Fregister%2Fproxy%2F1%2F100027");
            System.out.println(url);
        } catch (DecoderException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
