package com.panda.zoo.common.test.java.model;

import java.io.Serializable;

/**
 * Created by huixiangdou on 2017/5/19.
 */
public class SP implements Serializable{
    private static final long serialVersionUID = 586792542924037568L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
