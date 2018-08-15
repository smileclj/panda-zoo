package com.panda.zoo.common.test.java.thread;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author huixiangdou
 * @date 2018/7/27
 */
public class CollectionTest {
    @Test
    public void deque() {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addFirst("1");

        System.out.println(Integer.toBinaryString(-1));

        System.out.println(-1 & 15);
        System.out.println(-16 & 15);
    }
}
