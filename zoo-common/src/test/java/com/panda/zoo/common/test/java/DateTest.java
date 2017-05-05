package com.panda.zoo.common.test.java;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by huixiangdou on 2017/4/13.
 */
public class DateTest {
    @Test
    public void add() {
        Date now = new Date();
        System.out.println("当前时间：" + now.getTime());
        System.out.println("7天前：" + DateUtils.addDays(now, -7).getTime());
        System.out.println("3个月前：" + DateUtils.addMonths(now, -3).getTime());
    }

    @Test
    public void format() {
        System.out.println(DateFormatUtils.format(DateUtils.addDays(new Date(), -7), "yyyy-MM-dd"));
    }
}
