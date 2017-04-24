package com.panda.zoo.common.test.java;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

/**
 * Created by huixiangdou on 2017/4/17.
 */
public class CommonUtilTest {
    @Test
    public void testBooleanUtils1(){
        System.out.println(BooleanUtils.toBoolean("1"));
        System.out.println(BooleanUtils.toBoolean("0"));
        System.out.println("============================");
        System.out.println(BooleanUtils.toBoolean(1));
        System.out.println(BooleanUtils.toBoolean(0));
        System.out.println("============================");
        System.out.println(BooleanUtils.toBoolean(2));
        System.out.println(BooleanUtils.toBoolean(-1));
    }
}
