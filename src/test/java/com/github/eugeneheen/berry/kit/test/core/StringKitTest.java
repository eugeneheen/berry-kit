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
    public void testFormart() {
        String result = stringKit.formart("您好，{0}，欢迎您，<a href='\"http://www.163.com\"'>点击领取会员卡</a>。", new Object [] {"志恒石油集团"});
        System.out.println(result);
    }
}
