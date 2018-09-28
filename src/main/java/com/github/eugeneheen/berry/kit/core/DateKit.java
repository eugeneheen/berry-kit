package com.github.eugeneheen.berry.kit.core;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

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
     * 符合国际标准的时间日期格式yyyy-MM-dd HH:mm:ss，例如：2017-01-01 15:10:50
     */
    public static final String ISO_DATETIMESS_FORMART = "yyyy-MM-dd HH:mm:ss";

    /**
     * 符合国际标准的时间日期格式yyyy-MM-dd HH:mm，例如：2017-01-01 15:10
     */
    public static final String ISO_DATETIME_FORMART = "yyyy-MM-dd HH:mm:ss";

    /**
     * 符合国际标准的日期格式yyyy-MM，例如：2017-01
     */
    public static final String ISO_YEAR_OF_MONTH_FORMART = "yyyy-MM";

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
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期时间字符串
     */
    public String dataTimeFormart(Date date) {
        return DateFormatUtils.format(date, ISO_DATETIME_FORMART);
    }

    /**
     * 日期时间格式化
     *
     * @param calendar java.util.Calendar对象
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期时间字符串
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
     * 统计指定年所在月份的天数
     *
     * @param year  年
     * @param month 月
     * @return 月的总天数
     * @throws ParseException 解析日期发生异常
     */
    public int countDay(int year, int month) throws ParseException {
        Calendar calendar = getCalendar(year, month);
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
        Calendar calendar = getCalendar(year, month);
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
        return this.toCalendar(date).get(Calendar.MONTH) + 1;
    }

    /**
     * 获取月
     * @param calendar java.util.Calendar对象
     * @return 月
     */
    public int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
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
        return this.toCalendar(date).get(Calendar.DAY_OF_MONTH) - 1;
    }

    /**
     * 获取星期
     * @param calendar java.util.Calendar对象
     * @return 星期
     */
    public int getWeekNum(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
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

        switch (this.getWeekNum(calendar) + 1) {
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
     * 获取年龄
     * @param birthday java.util.Calendar对象
     * @return 年龄
     */
    public int getAge(Calendar birthday) {
        Calendar currentDate = Calendar.getInstance();
        return currentDate.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
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
     * 获取日历
     * @param year 年
     * @param month 月
     * @return java.util.Calendar对象
     * @throws ParseException 解析日期发生异常
     */
    private Calendar getCalendar(int year, int month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        StringKit stringKit = new StringKit();
        String date = stringKit.join("-", new Object[]{year, month});
        calendar.setTime(DateUtils.parseDate(date, ISO_YEAR_OF_MONTH_FORMART));
        return calendar;
    }
}
