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
}
