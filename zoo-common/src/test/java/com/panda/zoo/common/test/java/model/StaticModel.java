package com.panda.zoo.common.test.java.model;

/**
 * Created by huixiangdou on 2017/2/25.
 */
public class StaticModel {
    public static String id;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        StaticModel.id = id;
    }
}
