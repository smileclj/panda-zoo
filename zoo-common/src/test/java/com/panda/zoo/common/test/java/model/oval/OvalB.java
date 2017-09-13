package com.panda.zoo.common.test.java.model.oval;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * Created by huixiangdou on 2017/7/7.
 */
public class OvalB {
    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private OvalP ovalP;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OvalP getOvalP() {
        return ovalP;
    }

    public void setOvalP(OvalP ovalP) {
        this.ovalP = ovalP;
    }
}
