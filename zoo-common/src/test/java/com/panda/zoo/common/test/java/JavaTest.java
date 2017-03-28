package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.panda.zoo.common.test.java.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
    public void testExtendV2(){
        ChildExtend childExtend = new ChildExtend();
        childExtend.say();
        System.out.println(childExtend.getId());
    }

}
