package com.panda.zoo.common.test.json.model;

/**
 * Created by huixiangdou on 2017/3/1.
 */
public class User {
    private transient Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
