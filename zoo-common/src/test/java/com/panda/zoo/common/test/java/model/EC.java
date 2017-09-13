package com.panda.zoo.common.test.java.model;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huixiangdou on 2017/7/14.
 */
public class EC extends EP {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Set<EC> set = new HashSet<>();
        EC ec1 = new EC();
        ec1.setId("1");

        EC ec2 = new EC();
        ec2.setId("1");
        set.add(ec1);
        set.add(ec2);
        System.out.println(JSON.toJSON(set));
    }
}
