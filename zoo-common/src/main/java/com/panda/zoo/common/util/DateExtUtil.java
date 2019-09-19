package com.panda.zoo.common.util;

import com.google.common.base.Splitter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by huixiangdou on 2017/7/3.
 * 日期扩展功率类
 */
public class DateExtUtil {
    /**
     * 时以下单位均为最小(24小时制)
     *
     * @param d
     * @return
     */
    public static Date getFirstHour(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 时以下单位均为最大(24小时制)
     *
     * @param d
     * @return
     */
    public static Date getLastHour(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 秒以下单位均为最大
     *
     * @param d
     * @return
     */
    public static Date getLastSecond(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 毫秒以下单位均为最大
     *
     * @param d
     * @return
     */
    public static Date getLastMilliSecond(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 获取星期几(对应于国内的星期)
     *
     * @param d
     * @return
     */
    public static int getWeekNum(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int week = c.get(Calendar.DAY_OF_WEEK) - 1;
        week = week == 0 ? 7 : week;
        return week;
    }

    /**
     * 根据给定时间获取Date，基于d
     * format - HH:mm
     *
     * @return
     */
    public static Date getDateFromTime(Date d, String time) {
        List<String> list = Splitter.on(":").splitToList(time);
        int hour = Integer.parseInt(list.get(0));
        int minute = Integer.parseInt(list.get(1));
        return Builder.build(getFirstHour(d)).setHour(hour).setMinute(minute).getTime();
    }

    public static class Builder {
        private int hour;
        private int minute;
        private int second;
        private int millisecond;
        private static Calendar c = Calendar.getInstance();

        private Builder() {

        }

        public static Builder build(Date d) {
            c.setTime(d);
            return new Builder();
        }

        public static Builder build(Calendar c) {
            Builder.c = c;
            return new Builder();
        }

        public static Builder build() {
            return new Builder();
        }

        public Builder setHour(int hour) {
            c.set(Calendar.HOUR, hour);
            return this;
        }

        public Builder setMinute(int minute) {
            c.set(Calendar.MINUTE, minute);
            return this;
        }

        public Builder setSecond(int second) {
            c.set(Calendar.SECOND, second);
            return this;
        }

        public Builder setMillisecond(int millisecond) {
            c.set(Calendar.MILLISECOND, millisecond);
            return this;
        }

        public Date getTime() {
            return c.getTime();
        }
    }

    public static void main(String[] args) {
        System.out.println(getFirstHour(new Date()));
        System.out.println(getLastHour(new Date()));
        System.out.println(getWeekNum(new Date()));
    }
}
