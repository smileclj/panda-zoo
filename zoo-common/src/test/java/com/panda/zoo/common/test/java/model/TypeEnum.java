package com.panda.zoo.common.test.java.model;

/**
 * Created by huixiangdou on 2017/4/26.
 */
public enum TypeEnum {
    A(1, "A"), B(2, "B");


    private int code;
    private String msg;

    TypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
