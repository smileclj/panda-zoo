package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.panda.zoo.common.test.java.model.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by huixiangdou on 2017/2/25.
 */
public class JavaTest {

    @Test
    public void testStatic() {
        StaticModel staticModel = new StaticModel();
        StaticModel.id = "2";
        staticModel.setId("1");
        System.out.println(staticModel.getId());
        System.out.println(StaticModel.id);
    }

    @Test
    public void testExtend() {
        Parent.id = "1";
        Child1.id = "2";
        Child2.id = "3";
        Child1 c1 = new Child1();
        c1.setId("4");
        System.out.println(Parent.id);
    }

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == 1) {
                it.remove();
            }
        }

        System.out.println(JSON.toJSONString(list));
    }

    private <T> List<T> get() {
        return new ArrayList<>();
    }

    @Test
    public void testExtendV2() {
        ChildExtend childExtend = new ChildExtend();
        childExtend.say();
        System.out.println(childExtend.getId());
    }

    @Test
    public void timeUnit() {
        System.out.println(TimeUnit.MINUTES.toMillis(3));
    }

    @Test
    public void calendar() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayOfWeek);
    }

    @Test
    public void extend() {
        Parent p1 = new Child1();
        Parent p2 = new Child2();

        System.out.println(p1);
        System.out.println(p2);
    }

    @Test
    public void testBoolean() {
        System.out.println(Boolean.valueOf("1"));
        System.out.println(Boolean.valueOf("0"));
        System.out.println(Boolean.valueOf("true"));
        System.out.println(Boolean.valueOf("false"));
        System.out.println(Boolean.valueOf(true));
    }

    @Test
    public void testArrayCopy() {
        Integer[] arr1 = new Integer[]{1, 2, 3, 4};
        Integer[] arr2 = new Integer[]{5, 6, 7, 8};
        System.arraycopy(arr1, 0, arr2, 2, 2);
    }
}
