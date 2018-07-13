package com.panda.zoo.common.test.java.oval;


import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * Created by huixiangdou on 2017/7/7.
 */
public class EntityB {
    @NotNull
    private String name;

    @AssertValid
    private EntityP ovalP;

    @NotNull
    @AssertValid
    private List<EntityD> entityDList;


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

    public List<EntityD> getEntityDList() {
        return entityDList;
    }

    public void setEntityDList(List<EntityD> entityDList) {
        this.entityDList = entityDList;
    }
}
