package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.StringKit;
import com.github.eugeneheen.berry.kit.exception.NoSupportLogLevel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringKitTest {

    private static StringKit stringKit;

    @BeforeClass
    public static void beforeClass() {
        stringKit = new StringKit();
    }

    @Test
    public void testRandom() {
        System.out.println(stringKit.randomByType(20, StringKit.RANDOM_TYPE_ALPHABETIC));
        System.out.println(stringKit.randomByType(20, StringKit.RANDOM_TYPE_NUMBER));
        System.out.println(stringKit.randomByType(20, StringKit.RANDOM_TYPE_CHARACTERS));

    }

    @Test
    public void testUUID() {
        System.out.println(stringKit.uuid(true));

    }

    @Test
    public void testLog() {

        final String infoExpected = ">>>>>>>>Info>>>>>>>>纪录日志消息";
        final String debugExpected = "#######Debug########调试日志消息";
        final String errorExpected = "*******Error********错误日志消息";
        try {
            String info = stringKit.log("纪录日志消息", StringKit.LOG_LEVEL_INFO);
            String debug = stringKit.log("调试日志消息", StringKit.LOG_LEVEL_DEBUG);
            String error = stringKit.log("错误日志消息", StringKit.LOG_LEVEL_ERROR);
            Assert.assertEquals(infoExpected, info);
            Assert.assertEquals(debugExpected, debug);
            Assert.assertEquals(errorExpected, error);
        } catch (NoSupportLogLevel noSupportLogLevel) {
            noSupportLogLevel.printStackTrace();
        }
    }
}
