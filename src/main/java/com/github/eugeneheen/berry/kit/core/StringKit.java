package com.github.eugeneheen.berry.kit.core;

import com.github.eugeneheen.berry.kit.exception.NoSupportLogLevel;
import com.github.eugeneheen.berry.kit.exception.RandomException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * 字符串工具箱，提供基本的字符串处理实现<br>
 * @author Eugene
 */
public class StringKit {

    /**
     * 处理规则，null、""、" "均转换为null
     */
    public static final String TRIM_RULE_NULL = "null";

    /**
     * 处理规则，null、""、" "均转换为""
     */
    public static final String TRIM_RULE_EMPTY = "empty";

    /**
     * 随机混合字符串
     */
    public static final String RANDOM_TYPE_CHARACTERS = "character";

    /**
     * 随机全数字字符串
     */
    public static final String RANDOM_TYPE_NUMBER = "number";

    /**
     * 随机全字母字符串
     */
    public static final String RANDOM_TYPE_ALPHABETIC = "alphabetic";

    /**
     * INFO级别日志
     */
    public static final String LOG_LEVEL_INFO = "info";

    /**
     * ERROR级别日志
     */
    public static final String LOG_LEVEL_ERROR = "error";

    /**
     * DEBUG级别日志
     */
    public static final String LOG_LEVEL_DEBUG = "debug";

    /**
     * 构造方法
     */
    public StringKit() {
    }

    /**
     * 验证字符串是否包含指定验证内容，忽略字母大小写
     *
     * @param str       字符串
     * @param searchStr 验证内容
     * @return 字符串包含验证内容返回true，反之返回false
     */
    public boolean contains(String str, String searchStr) {
        return StringUtils.contains(str, searchStr);
    }

    /**
     * 验证字符串是否包含指定验证内容，isIgnoreCase参数为true，会忽略字母大小写，反之不忽略字母大小写
     *
     * @param str          字符串
     * @param searchStr    验证内容
     * @param isIgnoreCase 是否忽略大小写
     * @return 字符串包含验证内容返回true，反之返回false
     */
    public boolean contains(String str, String searchStr, boolean isIgnoreCase) {
        if (isIgnoreCase) {
            return StringUtils.containsIgnoreCase(str, searchStr);
        } else {
            return this.contains(str, searchStr);
        }
    }

    /**
     * 验证字符串是否包含指定的一组验证内容中的其中一个，忽略字母大小写
     *
     * @param str        字符串
     * @param searchStrs 一组验证内容
     * @return 字符串只要包含一组验证内容中的一个则返回true，反之返回false
     */
    public boolean contains(String str, String[] searchStrs) {
        return StringUtils.containsAny(str, searchStrs);
    }

    /**
     * 检索字符串中出现验证内容的次数
     *
     * @param str    字符串
     * @param search 验证内容
     * @return 验证内容的统计数量
     */
    public int count(String str, String search) {
        return StringUtils.countMatches(str, search);
    }

    /**
     * 验证字符串是否已suffixs指定的后缀结尾
     *
     * @param str     字符串
     * @param suffixs 后缀
     * @return 字符串已指定后缀结尾返回true，反之返回false
     */
    public boolean endsWith(String str, String... suffixs) {
        return StringUtils.endsWithAny(str, suffixs);
    }

    /**
     * 验证字符串是否已suffixs指定的前缀开始
     *
     * @param str     字符串
     * @param prefixs 前缀
     * @return 字符串已指定前缀开始返回true，反之返回false
     */
    public boolean startsWith(String str, String... prefixs) {
        return StringUtils.startsWithAny(str, prefixs);
    }

    /**
     * 验证字符串长度是否为指定的预期值
     *
     * @param str    字符串
     * @param expect 预期值
     * @return 长度为预期值返回true，否则返回false
     */
    public boolean length(String str, int expect) {
        return StringUtils.length(str) == expect;
    }

    /**
     * 验证字符串长度是否为指定的预期值，isWhitespace为true则移除前后空格
     *
     * @param str          字符串
     * @param expect       预期值
     * @param isWhitespace 是否移除前后空格
     * @return 长度为预期值返回true，否则返回false
     */
    public boolean length(String str, int expect, boolean isWhitespace) {
        if (isWhitespace) {
            String withoutTrim = this.trim(str, TRIM_RULE_EMPTY);
            return this.length(withoutTrim, expect);
        } else {
            return this.length(str, expect);
        }
    }


    /**
     * 检查字符串是否为空字符串""或null值
     *
     * @param str 字符串
     * @return 空字符串""或null返回true；反之返回false
     */
    public boolean isEmpty(CharSequence str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 检查字符串是否为空值
     * <ul>
     * <li>如果isWhitespace指定为true，空值匹配规则为空格" "、空字符串""或null值</li>
     * <li>如果isWhitespace指定为false，空值匹配规则为空字符串""或null值</li>
     * </ul>
     *
     * @param str          字符串
     * @param isWhitespace 是否检查空格
     * @return 匹配规则返回true；反之返回false
     */
    public boolean isEmpty(CharSequence str, boolean isWhitespace) {
        if (isWhitespace) {
            return StringUtils.isBlank(str);
        } else {
            return this.isEmpty(str);
        }
    }

    /**
     * 检查任意一个字符串是否为空字符串""或null值
     *
     * @param str 字符串
     * @return 任意一个字符串匹配，空字符串""或null值返回ture，反之返回false
     */
    public boolean isAnyEmpty(CharSequence... str) {
        return StringUtils.isAnyEmpty(str);
    }

    /**
     * 检查任意一个字符串是否为空字符串""、null值或空格" "
     * <ul>
     * <li>如果isWhitespace指定为true，空值匹配规则为空格" "、空字符串""或null值</li>
     * <li>如果isWhitespace指定为false，空值匹配规则为空字符串""或null值</li>
     * </ul>
     *
     * @param str          字符串
     * @param isWhitespace 是否检查空格
     * @return 匹配规则返回true；反之返回false
     */
    public boolean isAnyEmpty(boolean isWhitespace, CharSequence... str) {
        if (isWhitespace) {
            return StringUtils.isAnyBlank(str);
        } else {
            return this.isAnyEmpty(str);
        }
    }

    /**
     * 移除字符串前后的空格，""或null值将直接返回
     *
     * @param str 字符串
     * @return 返回处理后的字符串
     */
    public String trim(String str) {
        return StringUtils.trim(str);
    }

    /**
     * 指定规则处理字符串前后的空格
     *
     * @param str      字符串
     * @param trimRule 使用TRIM_RULE_NULL、TRIM_RULE_EMPTY常量指定空格处理规则
     * @return 按照规则处理后的字符串
     */
    public String trim(String str, String trimRule) {
        switch (trimRule) {
            case TRIM_RULE_NULL:
                return StringUtils.trimToNull(str);
            case TRIM_RULE_EMPTY:
                return StringUtils.trimToEmpty(str);
            default:
                return this.trim(str);
        }
    }

    /**
     * 拼接字符串
     *
     * @param elements 待拼接的元素
     * @return 拼接后的字符串
     */
    public String join(Object[] elements) {
        return StringUtils.join(elements);
    }

    /**
     * 拼接字符串
     *
     * @param elements 待拼接的元素
     * @return 拼接后的字符串
     */
    public String join(List<String> elements) {
        return this.join(elements.toArray());
    }

    /**
     * 使用分隔符拼接字符串
     *
     * @param separator 分隔符
     * @param elements  待拼接的元素
     * @return 拼接后的字符串
     */
    public String join(String separator, Object[] elements) {
        return StringUtils.joinWith(separator, elements);
    }

    /**
     * 使用分隔符拼接字符串
     *
     * @param separator 分隔符
     * @param elements  待拼接的元素
     * @return 拼接后的字符串
     */
    public String join(String separator, List<String> elements) {
        return StringUtils.joinWith(separator, elements.toArray());
    }

    /**
     * 删除str参数中，全部匹配remove参数（String）指定的字符串
     *
     * @param str    源字符串
     * @param remove 需要删除的字符串
     * @return 删除指定字符串的一个新字符串
     */
    public String remove(String str, String remove) {
        return StringUtils.remove(str, remove);
    }

    /**
     * 删除str参数中，全部匹配remove参数（char）指定的字符串
     *
     * @param str    字符串
     * @param remove 需要删除的字符串
     * @return 删除指定字符串的一个新字符串
     */
    public String remove(String str, char remove) {
        return StringUtils.remove(str, remove);
    }

    /**
     * 替换字符串中，目标内容为新指定的内容
     *
     * @param str         字符串
     * @param searchStr   目标内容（区分字母大小写）
     * @param replacement 新指定的内容
     * @return 已经替换内容的新字符串
     */
    public String replace(String str, String searchStr, String replacement) {
        return StringUtils.replace(str, searchStr, replacement);
    }

    /**
     * 替换字符串中，目标内容为新指定的内容，isIgnoreCase为true，对比内容时忽略字母大小写，反之不忽略字母大小写
     *
     * @param str          字符串
     * @param searchStr    目标内容（是否区分字母大小写取决于）
     * @param replacement  新指定的内容
     * @param isIgnoreCase 是否忽略大小写
     * @return 已经替换内容的新字符串
     */
    public String replace(String str, String searchStr, String replacement, boolean isIgnoreCase) {
        if (isIgnoreCase) {
            return StringUtils.replaceIgnoreCase(str, searchStr, replacement);
        } else {
            return this.replace(str, searchStr, replacement);
        }

    }

    /**
     * 验证字符串内容是否一致
     *
     * @param actual  实际值
     * @param expects 预期值
     * @return 内容一致返回true，反之返回false
     */
    public boolean equals(String actual, String... expects) {
        return StringUtils.equalsAny(actual, expects);
    }

    /**
     * 验证字符串内容是否一致，isIgnoreCase为true，对比内容时忽略字母大小写，反之不忽略字母大小写
     *
     * @param actual       实际值
     * @param expects      预期值
     * @param isIgnoreCase 是否忽略大小写
     * @return 内容一致返回true，反之返回false
     */
    public boolean equals(boolean isIgnoreCase, String actual, String... expects) {
        if (isIgnoreCase) {
            return StringUtils.equalsAnyIgnoreCase(actual, expects);
        } else {
            return this.equals(actual, expects);
        }
    }

    /**
     * 生成带"-"，长度36位的UUID
     *
     * @return UUID字符串
     */
    public String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成UUID
     * <ul>
     * <li>isClean位true时，生成不带"-"，长度32位的UUID</li>
     * <li>isClean位false时，生成带"-"，长度36位的UUID</li>
     * </ul>
     *
     * @param isClean 是否移除"-"分隔
     * @return UUID字符串
     */
    public String uuid(boolean isClean) {
        if (isClean) {
            String uuid = this.uuid();
            return this.remove(uuid, "-");
        } else {
            return this.uuid();
        }
    }

    /**
     * 生成count参数指定位数的随机字符串，可设置生成内容为character字符、ascii码、数字类型，没有匹配类型默认使用character字符
     *
     * @param count       随机数位数
     * @param contentType 随机字符串内容的类型，可使用常量RANDOM_TYPE_NUMBER（数字）、RANDOM_TYPE_ALPHABETIC（字母）、RANDOM_TYPE_CHARACTERS（字母数字）指定类型
     * @return 随机字符串
     * @throws RandomException 随机数异常
     */
    public String randomByType(int count, String contentType) {
        switch (contentType) {
            case RANDOM_TYPE_NUMBER:
                return new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(count);
            case RANDOM_TYPE_ALPHABETIC:
                return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(count);
            case RANDOM_TYPE_CHARACTERS:
                return new RandomStringGenerator.Builder().withinRange('0', 'z').build().generate(count);
            default:
                throw new RandomException("生成随机数出错，不支持当前指定的随机数类型！");
        }

    }

    /**
     * 反向字符串
     *
     * @param str 字符串
     * @return 反向后的字符串
     */
    public String reverse(String str) {
        return StringUtils.reverse(str);
    }

    /**
     * 填充字符串模板，需遵循规范使用{下标（下标从0开始）}在字符串模板中充当占位符，例如：
     * <ul>
     * <li>字符串模板："我的姓名是{0}，年龄{1}"</li>
     * <li>使用字符串数组填充：new String [] {"Eugene", "18"}</li>
     * </ul>
     *
     * @param formartStr 字符串模板
     * @param fillValues 填充模板的值
     * @return 填充后的字符串
     */
    public String formart(String formartStr, Object... fillValues) {
        return MessageFormat.format(formartStr, fillValues);
    }

    /**
     * 生成指定级别的日志信息字符串
     * @param messsage 日志信息
     * @param level    日志级别
     * @return 规范格式的日志信息
     * @throws NoSupportLogLevel 的日志级别不被支持异常
     */
    public String log(String messsage, String level) throws NoSupportLogLevel {
        switch (level) {
            case LOG_LEVEL_INFO:
                return this.join(new Object[]{StringUtils.center("Info",20, ">"), messsage});
            case LOG_LEVEL_ERROR:
                return this.join(new Object[]{StringUtils.center("Error", 20, "*"), messsage});
            case LOG_LEVEL_DEBUG:
                return this.join(new Object[]{StringUtils.center("Debug", 20, "#"), messsage});
            default:
                throw new NoSupportLogLevel(level + "日志级别系统不支持!");
        }
    }
}
