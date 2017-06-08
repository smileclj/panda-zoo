package com.panda.zoo.common.test.java.model.copy;

/**
 * Created by huixiangdou on 2017/5/18.
 */
public class StudentBO {
    private Integer id;
    private SexEnum sex;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
