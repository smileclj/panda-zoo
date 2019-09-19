package com.panda.zoo.common.test.java;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Random;
import java.util.RandomAccess;

/**
 * @author huixiangdou
 * @date 2018/10/9
 */
public class RandomTest {
    @Test
    public void test1() {
        System.out.println(RandomUtils.nextInt(0, 3));
        System.out.println(RandomUtils.nextInt(0, 3));
        System.out.println(RandomUtils.nextInt(0, 3));
        System.out.println(RandomUtils.nextInt(0, 3));
        System.out.println(RandomUtils.nextInt(0, 3));
        System.out.println(RandomUtils.nextInt(0, 3));

    }
}
