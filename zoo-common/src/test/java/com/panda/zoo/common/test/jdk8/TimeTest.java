package com.panda.zoo.common.test.jdk8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author huixiangdou
 * @date 2018/5/29
 */
public class TimeTest {
    @Test
    public void format(){
        String date = "yyyy/MM/dd";
        System.out.println(DateTimeFormatter.ofPattern(date).format(LocalDate.now()));
    }
}
