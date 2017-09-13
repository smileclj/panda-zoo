package com.panda.zoo.common.test.java;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huixiangdou on 2017/9/8.
 */
public class ExceptionTest {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    @Test
    public void test() {
        try {
            throw new RuntimeException("111", new Throwable("111"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("1", e);
        }

    }
}
