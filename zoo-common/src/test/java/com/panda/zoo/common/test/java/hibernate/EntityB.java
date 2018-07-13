package com.panda.zoo.common.test.java.hibernate;


import net.sf.oval.constraint.NotNull;

/**
 * Created by huixiangdou on 2017/7/7.
 */
public class EntityB {
    @NotNull
    private String name;

    @NotNull
    private EntityP ovalP;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityP getOvalP() {
        return ovalP;
    }

    public void setOvalP(EntityP ovalP) {
        this.ovalP = ovalP;
    }
}
