package com.panda.zoo.common.test.java;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created by huixiangdou on 2017/4/17.
 */
public class CommonUtilTest {
    @Test
    public void testBooleanUtils1() {
        System.out.println(BooleanUtils.toBoolean("1"));
        System.out.println(BooleanUtils.toBoolean("0"));
        System.out.println("============================");
        System.out.println(BooleanUtils.toBoolean(1));
        System.out.println(BooleanUtils.toBoolean(0));
        System.out.println("============================");
        System.out.println(BooleanUtils.toBoolean(2));
        System.out.println(BooleanUtils.toBoolean(-1));
    }

    @Test
    public void random() {
        RandomUtils.nextBytes(1);
    }

    @Test
    public void format() {
        String format = "你好{}，小明{}";
        System.out.println(MessageFormatter.arrayFormat(format, new Object[]{1, "2"}).getMessage());
    }

    @Test
    public void assertj(){
        Assertions.assertThat(Lists.newArrayList()).isNotEmpty();
    }
}
