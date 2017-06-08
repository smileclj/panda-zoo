package com.panda.zoo.common.test.java.model.copy;

/**
 * Created by huixiangdou on 2017/5/18.
 */
public enum SexEnum {
    MALE(1, "男"), FEMALE(2, "女");

    private int code;
    private String msg;

    SexEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
