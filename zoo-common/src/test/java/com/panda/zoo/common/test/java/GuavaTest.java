package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
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

    @Test
    public void collection() {
        System.out.println(JSON.toJSON(Lists.newArrayList(Sets.newHashSet(Lists.newArrayList(1, 2, 3, 4, 5)))));
    }

    @Test
    public void removeIf() {
        List<String> list = Lists.newArrayList("1", "1", "2");

        Iterables.removeIf(list, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.equals("1");
            }
        });

        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void filter() {
        List<String> list = Lists.newArrayList("1", "1", "2");

        Iterable<String> list2 =  Iterables.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.equals("1");
            }
        });
        System.out.println(JSON.toJSONString(list2));
    }
}
