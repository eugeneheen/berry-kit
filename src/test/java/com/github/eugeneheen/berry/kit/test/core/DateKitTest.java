package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.DateKit;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述类提供的功能
 *
 * @author Eugene
 * 2019-01-18 14:00
 */
public class DateKitTest {

    @Test
    public void testGetAgeByCalendar() {
        DateKit dateKit = new DateKit();
        Calendar birthday = Calendar.getInstance();
        birthday.set(2017, 1, 20);
        int age = dateKit.getAge(birthday);
        System.out.println(age);

        age = dateKit.getAge(birthday.getTime());
        System.out.println(age);

        Date date = dateKit.addDays(birthday, 5);
        System.out.println(dateKit.dataTimeFormart(date));
    }

    @Test
    public void testParse() {
        final String date = "1990-10-01 11:00:01";
        DateKit dateKit = new DateKit();
        try {
            Date d = dateKit.parseYearMonth(date);
            System.out.println(dateKit.formart(d, "yyyy"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCalendar() {
        DateKit dateKit = new DateKit();
        Calendar calendar = dateKit.getCalendar(2014, 12);
        String date = dateKit.formart(calendar, DateKit.ISO_YEAR_OF_MONTH_FORMART);
        System.out.println(date);
    }

    @Test
    public void testCompare() {
        DateKit dateKit = new DateKit();
        String first = "2018-01-03 14:00:50";
        String second = "2018-01-03 14:00:50";
        String third = "2018-01-04 14:00:50";

        try {
            int result = dateKit.compare(dateKit.parse(first, DateKit.ISO_DATE_FORMART), dateKit.parse(second, DateKit.ISO_DATE_FORMART));
            Assert.assertEquals(0, result);
            result = dateKit.compare(dateKit.parse(first, DateKit.ISO_DATE_FORMART), dateKit.parse(third, DateKit.ISO_DATE_FORMART));
            Assert.assertEquals(-1, result);
            result = dateKit.compare(dateKit.parse(third, DateKit.ISO_DATE_FORMART), dateKit.parse(first, DateKit.ISO_DATE_FORMART));
            Assert.assertEquals(1, result);

            boolean actual = dateKit.afterThan(dateKit.parse(third, DateKit.ISO_DATETIMESS_FORMART), dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART));
            Assert.assertTrue(actual);
            actual = dateKit.beforeThan(dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART), dateKit.parse(third, DateKit.ISO_DATETIMESS_FORMART));
            Assert.assertTrue(actual);
            actual = dateKit.equalTo(dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART), dateKit.parse(second, DateKit.ISO_DATETIMESS_FORMART));
            Assert.assertTrue(actual);

            actual = dateKit.equalTo(dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART), dateKit.addDays(dateKit.parse(third, DateKit.ISO_DATETIMESS_FORMART), -1));
            Assert.assertTrue(actual);

            actual = dateKit.equalTo(dateKit.parse(third, DateKit.ISO_DATETIMESS_FORMART), dateKit.addDays(dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART), 1));
            Assert.assertTrue(actual);

            actual = dateKit.equalTo(dateKit.parse(third, DateKit.ISO_DATETIMESS_FORMART), dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART), DateKit.FIELD_DAY, 1);
            Assert.assertTrue(actual);
            actual = dateKit.equalTo(dateKit.parse(first, DateKit.ISO_DATETIMESS_FORMART), dateKit.parse(third, DateKit.ISO_DATETIMESS_FORMART), DateKit.FIELD_DAY, -1);
            Assert.assertTrue(actual);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMillisecondToSecond() {
        DateKit dateKit = new DateKit();
        // 2小时
        long minute = dateKit.millisecondToSecond(7200000);
        Assert.assertEquals(7200, minute);
        // 1.5小时
        minute = dateKit.millisecondToSecond(5400000);
        Assert.assertEquals(5400, minute);
        // 15分钟
        minute = dateKit.millisecondToSecond(900000);
        Assert.assertEquals(900, minute);
    }

    @Test
    public void testMillisecondToMinute() {
        DateKit dateKit = new DateKit();
        // 2小时
        long minute = dateKit.millisecondToMinute(7200000);
        Assert.assertEquals(120, minute);
        // 1.5小时
        minute = dateKit.millisecondToMinute(5400000);
        Assert.assertEquals(90, minute);
        // 15分钟
        minute = dateKit.millisecondToMinute(900000);
        Assert.assertEquals(15, minute);
    }

    @Test
    public void testSecondToMillisecond() {
        DateKit dateKit = new DateKit();
        // 2小时
        long minute = dateKit.secondToMillisecond(7200);
        Assert.assertEquals(7200000, minute);
        // 1.5小时
        minute = dateKit.secondToMillisecond(5400);
        Assert.assertEquals(5400000, minute);
        // 15分钟
        minute = dateKit.secondToMillisecond(900);
        Assert.assertEquals(900000, minute);
    }

    @Test
    public void testSecondToMinute() {
        DateKit dateKit = new DateKit();
        // 2小时
        long minute = dateKit.secondToMinute(7200);
        Assert.assertEquals(120, minute);
        // 1.5小时
        minute = dateKit.secondToMinute(5400);
        Assert.assertEquals(90, minute);
        // 15分钟
        minute = dateKit.secondToMinute(900);
        Assert.assertEquals(15, minute);
    }

    @Test
    public void testMinuteToMillisecond() {
        DateKit dateKit = new DateKit();
        // 15分钟
        long hour = dateKit.minuteToMillisecond(15);
        Assert.assertEquals(900000, hour);
        // 90分钟
        hour = dateKit.minuteToMillisecond(90);
        Assert.assertEquals(5400000, hour);
        // 120分钟
        hour = dateKit.minuteToMillisecond(120);
        Assert.assertEquals(7200000, hour);
    }

    @Test
    public void testMinuteToSecond() {
        DateKit dateKit = new DateKit();
        // 15分钟
        long hour = dateKit.minuteToSecond(15);
        Assert.assertEquals(900, hour);
        // 90分钟
        hour = dateKit.minuteToSecond(90);
        Assert.assertEquals(5400, hour);
        // 120分钟
        hour = dateKit.minuteToSecond(120);
        Assert.assertEquals(7200, hour);
    }
}
