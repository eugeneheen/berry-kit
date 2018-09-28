package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.CodecKit;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
}
