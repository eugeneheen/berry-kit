package com.github.eugeneheen.berry.kit.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 封装了BigDecimal的常用操作，提供便捷小数运算工具方法，解决计算精度相关问题。
 *
 * @author Eugene
 * 2019-02-25 12:48
 */
public class BigDecimalKit {

    /**
     * 除法运算默认精度
     */
    private static final int DEFAULT_DIV_SCALE = 10;

    public BigDecimalKit() {

    }

    /**
     * RMB单位转换，元转换为分
     * @param price RMB（单位：元），数据类型double
     * @return RMB(单位：分)，数据类型long
     */
    public long transform2F(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        price = Double.valueOf(decimalFormat.format(price));
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(100)).longValue();
    }

    /**
     * RMB单位转换，分转换为元
     * @param price RMB(单位：分)，数据类型long
     * @return RMB（单位：元），数据类型double
     */
    public double transform2Y(long price) {
        return BigDecimal.valueOf(price).divide(BigDecimal.valueOf(100),2,  BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确加法
     * @param value1 第一个参数
     * @param value2 第二个参数
     * @return 第一个参数和第二个参数相加之后结果
     */
    public double add(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确加法
     * @param value1 第一个参数，参数类型String
     * @param value2 第二个参数，参数类型String
     * @return 第一个参数和第二个参数相加之后结果
     */
    public double add(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确减法
     * @param value1 第一个参数，参数类型double
     * @param value2 第二个参数，参数类型double
     * @return 第一个参数和第二个参数相减之后结果
     */
    public double sub(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确减法
     * @param value1 第一个参数，参数类型String
     * @param value2 第二个参数，参数类型String
     * @return 第一个参数和第二个参数相减之后结果
     */
    public double sub(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确乘法
     * @param value1 第一个参数，参数类型double
     * @param value2 第二个参数，参数类型double
     * @return 第一个参数和第二个参数相乘之后结果
     */
    public double mul(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 精确乘法
     * @param value1 第一个参数，参数类型String
     * @param value2 第一个参数，参数类型String
     * @return 第一个参数和第二个参数相乘之后结果
     */
    public double mul(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 使用默认精度，精确除法
     * @param value1 第一个参数，参数类型double
     * @param value2 第二个参数，参数类型double
     * @return 第一个参数和第二个参数相除之后结果
     * @throws IllegalAccessException 精度不被支持时，发生参数访问异常
     */
    public double div(double value1, double value2) throws IllegalAccessException {
        return this.div(value1, value2, BigDecimalKit.DEFAULT_DIV_SCALE);
    }

    /**
     * 使用默认精度，精确除法
     * @param value1 第一个参数，参数类型String
     * @param value2 第二个参数，参数类型String
     * @return 第一个参数和第二个参数相除之后结果
     * @throws IllegalAccessException 精度不被支持时，发生参数访问异常
     */
    public double div(String value1, String value2) throws IllegalAccessException {
        return this.div(value1, value2, BigDecimalKit.DEFAULT_DIV_SCALE);
    }

    /**
     * 使用指定精度，精度进行四舍五入处理，精确除法
     * @param value1 第一个参数，参数类型double
     * @param value2 第二个参数，参数类型double
     * @param scale 小数点后保留几位的精度
     * @return 第一个参数和第二个参数结合指定精度相除之后结果
     * @throws IllegalAccessException 精度不被支持时，发生参数访问异常。指定精度小于0时抛出异常
     */
    public double div(double value1, double value2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 使用指定精度，精确除法
     * @param value1 第一个参数，参数类型String
     * @param value2 第二个参数，参数类型String
     * @param scale 小数点后保留几位的精度
     * @return 第一个参数和第二个参数结合指定精度相除之后结果
     * @throws IllegalAccessException 精度不被支持时，发生参数访问异常。指定精度小于0时抛出异常
     */
    public double div(String value1, String value2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 四舍五入
     * @param value 四舍五入的参数，参数类型double
     * @param scale 小数点后保留几位的精度
     * @return 结合指定精度四舍五入之后结果
     * @throws IllegalAccessException  精度不被支持时，发生参数访问异常
     */
    public double round(double value, int scale) throws IllegalAccessException {
        final double DIVISOR = 1;
        return this.div(value, DIVISOR, scale);
    }

    /**
     * 四舍五入
     * @param value 四舍五入的参数，参数类型String
     * @param scale 小数点后保留几位的精度
     * @return 结合指定精度四舍五入之后结果
     * @throws IllegalAccessException  精度不被支持时，发生参数访问异常
     */
    public double round(String value, int scale) throws IllegalAccessException {
        final String DIVISOR = "1";
        return this.div(value, DIVISOR, scale);
    }

    /**
     * 比较是否相等
     * @param b1 第一个参数，参数类型BigDecimal
     * @param b2 第二个参数，参数类型BigDecimal
     * @return 判断结果相等返回true，反之返回false
     */
    public boolean equalTo(BigDecimal b1, BigDecimal b2) {
        if (b1 == null || b2 == null) {
            return false;
        }
        return 0 == b1.compareTo(b2);
    }
}
