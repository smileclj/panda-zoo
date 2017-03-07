package com.panda.zoo.common.test.java;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by huixiangdou on 2017/3/6.
 */
public class UuidTest {
    @Test
    public void uuid() {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
