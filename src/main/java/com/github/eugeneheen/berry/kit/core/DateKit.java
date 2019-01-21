package com.github.eugeneheen.berry.kit.core;

import com.github.eugeneheen.berry.kit.exception.NotSupportDateFieldException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 日期时间工具箱，提供最基本常用的日期时间处理实现。
 * </p>
 * @author Eugene
 */
public class DateKit {

    /**
     * 符合国际标准的时间日期格式yyyy-MM-dd，例如：2017-01-01
     */
    public static final String ISO_DATE_FORMART = "yyyy-MM-dd";

    /**
     * 符合国际标准的时间日期格式yyyy-MM-dd HH:mm:ss，例如：2017-01-01 15:10:50
     */
    public static final String ISO_DATETIMESS_FORMART = "yyyy-MM-dd HH:mm:ss";

    /**
     * 符合国际标准的时间日期格式yyyy-MM-dd HH:mm，例如：2017-01-01 15:10
     */
    public static final String ISO_DATETIME_FORMART = "yyyy-MM-dd HH:mm";

    /**
     * 符合国际标准的日期格式yyyy-MM，例如：2017-01
     */
    public static final String ISO_YEAR_OF_MONTH_FORMART = "yyyy-MM";

    /**
     * 表示日历的年
     */
    public static final int FIELD_YEAR = 1;

    /**
     * 表示日历的月
     */
    public static final int FIELD_MONTH = 2;

    /**
     * 表示日历的日
     */
    public static final int FIELD_DAY = 3;

    /**
     * 表示日历的小时
     */
    public static final int FIELD_HOURS = 4;

    /**
     * 表示日历的分钟
     */
    public static final int FIELD_MINUTES = 5;

    /**
     * java.util.Calendar日历的偏移量
     */
    public static final int CALENDAR_OFFSET = 1;

    /**
     * 构造方法
     */
    public DateKit() {
    }

    /**
     * 自定义日期转换格式
     * @param date java.util.Date对象
     * @param pattern 日期格式
     * @return 返回自定日期格式字符串
     */
    public String formart(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 自定义日期转换格式
     * @param calendar java.util.Calendar对象
     * @param pattern 日期格式
     * @return 返回自定日期格式字符串
     */
    public String formart(Calendar calendar, String pattern) {
        return DateFormatUtils.format(calendar, pattern);
    }

    /**
     * 自定义日期转换格式
     * @param date java.util.Date对象
     * @param pattern 日期格式
     * @return 返回自定日期格式java.util.Calendar对象
     * @throws ParseException 解析日期发生异常，日期字符串与解析格式不匹配发生异常（日期字符串内容可多于解析格式内容，例如：日期字符串是年-月-日 时:分:秒，解析格式yyyy-MM-dd HH:mm或yyyy-MM-dd均合法）
     */
    public Date formartDate(Date date, String pattern) throws ParseException {
        String dateStr = this.formart(date, pattern);
        return this.parse(dateStr, pattern);
    }

    /**
     * 日期格式化
     *
     * @param date java.util.Date对象
     * @return 返回yyyy-MM-dd格式的日期字符串
     */
    public String dateFormart(Date date) {
        return DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(date);
    }

    /**
     * 日期格式化
     *
     * @param calendar java.util.Calendar对象
     * @return yyyy-MM-dd格式的日期字符串
     */
    public String dateFormart(Calendar calendar) {
        return DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(calendar);
    }

    /**
     * 日期时间格式化
     *
     * @param date java.util.Date对象
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期时间字符串
     */
    public String dataTimeSsFormart(Date date) {
        return DateFormatUtils.format(date, ISO_DATETIMESS_FORMART);
    }

    /**
     * 日期时间格式化
     *
     * @param calendar java.util.Calendar对象
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期时间字符串
     */
    public String dataTimeSsFormart(Calendar calendar) {
        return DateFormatUtils.format(calendar, ISO_DATETIMESS_FORMART);
    }

    /**
     * 日期时间格式化
     *
     * @param date java.util.Date对象
     * @return 返回yyyy-MM-dd HH:mm格式的日期时间字符串
     */
    public String dataTimeFormart(Date date) {
        return DateFormatUtils.format(date, ISO_DATETIME_FORMART);
    }

    /**
     * 日期时间格式化
     *
     * @param calendar java.util.Calendar对象
     * @return 返回yyyy-MM-dd HH:mm格式的日期时间字符串
     */
    public String dataTimeFormart(Calendar calendar) {
        return DateFormatUtils.format(calendar, ISO_DATETIME_FORMART);
    }

    /**
     * 日期格式化
     *
     * @param date java.util.Date对象
     * @return 返回yyyy-MM格式的日期时间字符串
     */
    public String yearMonthFormart(Date date) {
        return DateFormatUtils.format(date, ISO_YEAR_OF_MONTH_FORMART);
    }

    /**
     * 日期格式化
     *
     * @param calendar java.util.Calendar对象
     * @return 返回yyyy-MM格式的日期时间字符串
     */
    public String yearMonthFormart(Calendar calendar) {
        return DateFormatUtils.format(calendar, ISO_YEAR_OF_MONTH_FORMART);
    }

    /**
     * 解析日期字符串为指定格式
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return java.util.Date对象
     * @throws ParseException 解析日期发生异常
     */
    public Date parse(String date, String pattern) throws ParseException {
        return FastDateFormat.getInstance(pattern).parse(date);
    }

    /**
     * 解析日期字符串，解析格式yyyy-MM-dd HH:mm:ss
     * @param date 日期字符串
     * @return java.util.Date对象
     * @throws ParseException 解析日期发生异常，日期字符串与解析格式不匹配发生异常（日期字符串内容可多于解析格式内容，例如：日期字符串是年-月-日 时:分:秒，解析格式yyyy-MM-dd HH:mm或yyyy-MM-dd均合法）
     */
    public Date parseTimeSs(String date) throws ParseException {
        return this.parse(date, DateKit.ISO_DATETIMESS_FORMART);
    }

    /**
     * 解析日期字符串，解析格式yyyy-MM-dd HH:mm
     * @param date 日期字符串
     * @return java.util.Date对象
     * @throws ParseException 解析日期发生异常，日期字符串与解析格式不匹配发生异常（日期字符串内容可多于解析格式内容，例如：日期字符串是年-月-日 时:分:秒，解析格式yyyy-MM-dd HH:mm或yyyy-MM-dd均合法）
     */
    public Date parseTime(String date) throws ParseException {
        return this.parse(date, DateKit.ISO_DATETIME_FORMART);
    }

    /**
     * 解析日期字符串，解析格式yyyy-MM
     * @param date 日期字符串
     * @return java.util.Date对象
     * @throws ParseException 解析日期发生异常，日期字符串与解析格式不匹配发生异常
     */
    public Date parseYearMonth(String date) throws ParseException {
        return this.parse(date, DateKit.ISO_YEAR_OF_MONTH_FORMART);
    }

    /**
     * 统计指定年所在月份的天数
     *
     * @param year  年
     * @param month 月
     * @return 月的总天数
     * @throws ParseException 解析日期发生异常
     */
    public int countDay(int year, int month) throws ParseException {
        Calendar calendar = this.getCalendar(year, month);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 统计指定年所在月份的周数
     *
     * @param year  年
     * @param month 月
     * @return 月的总周数
     * @throws ParseException 解析日期发生异常
     */
    public int countWeek(int year, int month) throws ParseException {
        Calendar calendar = this.getCalendar(year, month);
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取年
     * @param date java.util.Date对象
     * @return 年
     */
    public int getYear(Date date) {
        return this.toCalendar(date).get(Calendar.YEAR);
    }

    /**
     * 获取年
     * @param calendar java.util.Calendar对象
     * @return 年
     */
    public int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * @param date java.util.Date对象
     * @return 月
     */
    public int getMonth(Date date) {
        return this.toCalendar(date).get(Calendar.MONTH) + DateKit.CALENDAR_OFFSET;
    }

    /**
     * 获取月
     * @param calendar java.util.Calendar对象
     * @return 月
     */
    public int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + DateKit.CALENDAR_OFFSET;
    }

    /**
     * 获取天
     * @param date java.util.Date对象
     * @return 天
     */
    public int getDay(Date date) {
        return this.toCalendar(date).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取天
     * @param calendar java.util.Calendar对象
     * @return 天
     */
    public int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取星期
     * @param date java.util.Date对象
     * @return 星期
     */
    public int getWeekNum(Date date) {
        return this.toCalendar(date).get(Calendar.DAY_OF_MONTH) - DateKit.CALENDAR_OFFSET;
    }

    /**
     * 获取星期
     * @param calendar java.util.Calendar对象
     * @return 星期
     */
    public int getWeekNum(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) - DateKit.CALENDAR_OFFSET;
    }

    /**
     * 获取中文星期
     * @param calendar java.util.Calendar对象
     * @return 中文星期
     */
    public String getWeekZh(Calendar calendar) {
        if (calendar == null) {
            return null;
        }

        switch (this.getWeekNum(calendar) + DateKit.CALENDAR_OFFSET) {
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            default:
                return "星期日";
        }
    }

    /**
     * 获取虚岁年龄，例如：生日日期是1980-02-01，当前日期是1984-01-01，实际并未满2岁，但是虚岁年龄是2岁
     * @param birthday java.util.Calendar对象，生日日期
     * @return 虚岁年龄
     */
    public int getAge(Calendar birthday) {
        Calendar currentDate = Calendar.getInstance();
        return currentDate.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    /**
     * 获取虚岁年龄，例如：生日日期是1980-02-01，当前日期是1984-01-01，实际并未满2岁，但是虚岁年龄是2岁
     * @param birthday java.util.Date对象，生日日期
     * @return 虚岁年龄
     */
    public int getAge(Date birthday) {
        Calendar currentDate = Calendar.getInstance();
        int currentDateOfCalendar = currentDate.get(Calendar.YEAR);
        int birthdayCalendar = this.toCalendar(birthday).get(Calendar.YEAR);
        return currentDateOfCalendar - birthdayCalendar;
    }

    /**
     * 是否是闰年
     *
     * @param year 年
     * @return 是闰年返回ture，反之返回false
     */
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 转换为日历对象
     *
     * @param date java.util.Date对象
     * @return java.util.Calendar对象
     */
    public Calendar toCalendar(Date date) {
        return DateUtils.toCalendar(date);
    }

    /**
     * 指定的日期增加指定年数
     * @param date java.util.Date对象，指定的日期
     * @param amount 增加年数，正数表示增加，复数表示减少
     * @return 增加指定年数后的日期
     */
    public Date addYears(Date date, int amount) {
        return DateUtils.addYears(date, amount);
    }

    /**
     * 指定的日历增加指定年数
     * @param calendar java.util.Calendar对象，指定的日期
     * @param amount 增加年数，正数表示增加，复数表示减少
     * @return 增加指定年数后的日期
     */
    public Date addYears(Calendar calendar, int amount) {
        return this.addYears(calendar.getTime(), amount);
    }

    /**
     * 指定的日期增加指定月数
     * @param date java.util.Date对象，指定的日期
     * @param amount 增加月数，正数表示增加，复数表示减少
     * @return 增加指定月数后的日期
     */
    public Date addMonth(Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    /**
     * 指定的日历增加指定月数
     * @param calendar java.util.Calendar对象，指定的日期
     * @param amount 增加月数，正数表示增加，复数表示减少
     * @return 增加指定月数后的日期
     */
    public Date addMonth(Calendar calendar, int amount) {
        return this.addMonth(calendar.getTime(), amount);
    }

    /**
     * 指定的日期增加指定天数
     * @param date java.util.Date对象，指定的日期
     * @param amount 增加天数，正数表示增加，复数表示减少
     * @return 增加指定天数后的日期
     */
    public Date addDays(Date date, int amount) {
        return DateUtils.addDays(date, amount);
    }

    /**
     * 指定的日历增加指定天数
     * @param calendar java.util.Calendar对象，指定的日期
     * @param amount 增加天数，正数表示增加，复数表示减少
     * @return 增加指定天数后的日期
     */
    public Date addDays(Calendar calendar, int amount) {
        return this.addDays(calendar.getTime(), amount);
    }

    /**
     * 指定的日期增加指定小时数
     * @param date java.util.Date对象，指定的日期
     * @param amount 增加小时数，正数表示增加，复数表示减少
     * @return 增加指定小时数后的日期
     */
    public Date addHours(Date date, int amount) {
        return DateUtils.addHours(date, amount);
    }

    /**
     * 指定的日历增加指定小时数
     * @param calendar java.util.Calendar对象，指定的日期
     * @param amount 增加小时数，正数表示增加，复数表示减少
     * @return 增加指定小时数后的日期
     */
    public Date addHours(Calendar calendar, int amount) {
        return this.addHours(calendar.getTime(), amount);
    }

    /**
     * 指定的日期增加指定分钟数
     * @param date java.util.Date对象，指定的日期
     * @param amount 增加分钟数，正数表示增加，复数表示减少
     * @return 增加指定分钟数后的日期
     */
    public Date addMinutes(Date date, int amount) {
        return DateUtils.addMinutes(date, amount);
    }

    /**
     * 指定的日历增加指定分钟数
     * @param calendar java.util.Calendar对象，指定的日期
     * @param amount 增加分钟数，正数表示增加，复数表示减少
     * @return 增加指定分钟数后的日期
     */
    public Date addMinutes(Calendar calendar, int amount) {
        return this.addMinutes(calendar.getTime(), amount);
    }

    /**
     * 获取日历
     * @param year 年
     * @param month 月
     * @return java.util.Calendar对象
     */
    public Calendar getCalendar(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - DateKit.CALENDAR_OFFSET);
        return calendar;
    }

    /**
     * 比较日期，会比较年月日，时分秒毫秒
     * @param first 第一个日期
     * @param second 第二个日期
     * @return 第一个日期晚于第二个日期返回1，早于返回-1，相同返回0
     */
    public int compare(Date first, Date second) {

        if (first.getTime() > second.getTime()) {
            return 1;
        } else if (first.getTime() < second.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param first 第一个日期
     * @param second 第二个日期
     * @param pattern 日期格式
     * @return 第一个日期晚于第二个日期返回1，早于返回-1，相同返回0
     * @throws ParseException 解析日期发生异常，日期字符串与解析格式不匹配发生异常（日期字符串内容可多于解析格式内容，例如：日期字符串是年-月-日 时:分:秒，解析格式yyyy-MM-dd HH:mm或yyyy-MM-dd均合法）
     */
    public int compare(Date first, Date second, String pattern) throws ParseException {
        Date firstDate = this.formartDate(first, pattern);
        Date secondDate = this.formartDate(second, pattern);

        return this.compare(firstDate, secondDate);
    }

    /**
     * 时间比较晚于验证
     * @param first 第一个日期
     * @param second 第二个日期
     * @return 如果第一个日期晚于第二个日期返回true，否则返回false
     */
    public boolean afterThan(Date first, Date second) {
        return this.compare(first, second) == 1;
    }

    /**
     * 时间比较早于验证
     * @param first 第一个日期
     * @param second 第二个日期
     * @return 如果第一个日期早于第二个日期返回true，否则返回false
     */
    public boolean beforeThan(Date first, Date second) {
        return this.compare(first, second) == -1;
    }

    /**
     * 时间比较相同验证
     * @param first 第一个日期
     * @param second 第二个日期
     * @return 如果第一个日期与第二个日期相同返回true，否则返回false
     */
    public boolean equalTo(Date first, Date second) {
        return this.compare(first, second) == 0;
    }

    /**
     * 时间比较相同验证
     * @param first 第一个日期
     * @param second 第二个日期
     * @param field 日历字段，可通过DateKit前缀为FIELD_*的常量获取
     * @param offset 日历偏移量，正数表示增加，复数表示减少
     * @return 如果第一个日期与第二个日期相同返回true，否则返回false
     */
    public boolean equalTo(Date first, Date second, int field, int offset) {
        switch (field) {
            case DateKit.FIELD_YEAR:
                return this.equalTo(first, this.addYears(second, offset));
            case DateKit.FIELD_MONTH:
                return this.equalTo(first, this.addMonth(second, offset));
            case DateKit.FIELD_DAY:
                return this.equalTo(first, this.addDays(second, offset));
            case DateKit.FIELD_HOURS:
                return this.equalTo(first, this.addHours(second, offset));
            case DateKit.FIELD_MINUTES:
                return this.equalTo(first, this.addMinutes(second, offset));
            default:
                throw new NotSupportDateFieldException("不支持所提供的日历字段");
        }
    }
}
