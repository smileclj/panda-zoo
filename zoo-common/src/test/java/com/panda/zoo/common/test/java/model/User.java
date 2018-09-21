package com.panda.zoo.common.test.java.model;

/**
 * @author huixiangdou
 * @date 2018/9/6
 */
public class User<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
