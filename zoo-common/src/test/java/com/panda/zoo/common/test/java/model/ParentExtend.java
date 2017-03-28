package com.panda.zoo.common.test.java.model;

/**
 * Created by huixiangdou on 2017/3/21.
 */
public class ParentExtend {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void play(){
        System.out.println(getId());
    }
}
