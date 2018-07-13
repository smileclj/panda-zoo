package com.panda.zoo.common.test.java.oval;


import net.sf.oval.constraint.NotNull;

/**
 * Created by huixiangdou on 2017/7/7.
 */
public class EntityP {
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
