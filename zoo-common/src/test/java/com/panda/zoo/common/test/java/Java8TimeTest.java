package com.panda.zoo.common.test.java;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author huixiangdou
 * @date 2018/12/11
 */
public class Java8TimeTest {

    @Test
    public void test1() {
        System.out.println(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("yyyyMM").format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("yyyyww").format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now()));
    }
}
