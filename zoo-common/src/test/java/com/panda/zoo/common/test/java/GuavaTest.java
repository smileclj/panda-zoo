package com.panda.zoo.common.test.java;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by huixiangdou on 2017/4/17.
 */
public class GuavaTest {

    @Test
    public void method_iterable() {
        //list不能为null
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Integer[] i1 = FluentIterable.from(list).toArray(Integer.class);
    }
}
