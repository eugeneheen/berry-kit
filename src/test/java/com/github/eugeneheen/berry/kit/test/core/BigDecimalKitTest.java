package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.BigDecimalKit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BigDecimalKitTest {

    private static BigDecimalKit bigDecimalKit;

    @BeforeClass
    public static void beforeClass() {
        bigDecimalKit = new BigDecimalKit();
    }

    @Test
    public void testTransform() {
        final double YUAN = 5034.23;
        long fen = bigDecimalKit.transform2F(YUAN);
        Assert.assertEquals(503423, fen);
        double expectedYuan = bigDecimalKit.transform2Y(fen);
        Assert.assertEquals(expectedYuan, YUAN, 0.1);

        final double ZS = 5035;
        fen = bigDecimalKit.transform2F(ZS);
        Assert.assertEquals(503500, fen);
        expectedYuan = bigDecimalKit.transform2Y(fen);
        Assert.assertEquals(expectedYuan, ZS, 0.1);

        final double WW = 95006.03;
        fen = bigDecimalKit.transform2F(WW);
        Assert.assertEquals(9500603, fen);
        expectedYuan = bigDecimalKit.transform2Y(fen);
        Assert.assertEquals(expectedYuan, WW, 0.1);
    }
}
