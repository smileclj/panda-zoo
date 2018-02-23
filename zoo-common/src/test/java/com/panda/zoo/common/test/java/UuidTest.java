package com.panda.zoo.common.test.java;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by huixiangdou on 2017/3/6.
 */
public class UuidTest {
    @Test
    public void uuid() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }
}
