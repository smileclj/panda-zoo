package com.panda.zoo.common.test.java.time;

import cn.jiguang.common.Week;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.omg.CORBA.DATA_CONVERSION;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * @author huixiangdou
 * @date 2018/12/29
 */
public class LocalDateTimeTest {
    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse("2018-11-11 11:11:11"));
        System.out.println(localDateTime);
        System.out.println("年:" + localDateTime.getYear());
        System.out.println("月:" + localDateTime.getMonthValue());
        System.out.println("日:" + localDateTime.getDayOfMonth());
        System.out.println("时:" + localDateTime.getHour());
        System.out.println("分:" + localDateTime.getSecond());

        System.out.println(localDateTime.minus(1, ChronoUnit.DAYS));
        System.out.println(localDateTime.plus(1, ChronoUnit.DAYS));

        System.out.println(localDateTime.withMinute(0).withSecond(0));

        System.out.println(Instant.now().getEpochSecond());

    }

    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getNano());
        System.out.println(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(System.currentTimeMillis());
        System.out.println(localDateTime.withMinute(0).withSecond(0).toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Test
    public void test3() {
        LocalDate localDate1 = LocalDate.parse("2018-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localDate2 = LocalDate.parse("2020-01-03", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    @Test
    public void test4() {
        LocalDate localDate1 = LocalDate.parse("2018-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localDate2 = LocalDate.parse("2019-02-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(ChronoUnit.YEARS.between(localDate1, localDate2));
        System.out.println(ChronoUnit.MONTHS.between(localDate1, localDate2));
    }

    @Test
    public void test5() {
        LocalDateTime localDateTime1 = LocalDateTime.parse("2018-12-01 11:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime localDateTime2 = LocalDateTime.parse("2018-12-01 11:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(ChronoUnit.MINUTES.between(localDateTime1, localDateTime2));
    }

    @Test
    public void test6() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-12-31 11:11:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(localDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR));

        System.out.println(localDateTime.get(WeekFields.SUNDAY_START.weekBasedYear()));
        System.out.println(localDateTime.get(WeekFields.SUNDAY_START.weekOfWeekBasedYear()));
        System.out.println(localDateTime.get(WeekFields.SUNDAY_START.weekOfMonth()));
        System.out.println(localDateTime.get(WeekFields.SUNDAY_START.weekOfYear()));


//        String s = DateTimeFormatter.ofPattern("yyyyMMdd").format(localDateTime);
//        System.out.println(s);
    }

    @Test
    public void test7() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-12-31 11:11:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime.get(ChronoField.YEAR));
        System.out.println(localDateTime.get(ChronoField.YEAR_OF_ERA));
        System.out.println(localDateTime.get(ChronoField.ERA));

    }

    @Test
    public void pad(){
        System.out.println(StringUtils.leftPad("1",2,"0"));
        System.out.println(StringUtils.leftPad("12",2,"0"));
        System.out.println(StringUtils.leftPad("123",2,"0"));

    }
}
