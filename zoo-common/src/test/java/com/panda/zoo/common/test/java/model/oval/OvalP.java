package com.panda.zoo.common.test.java.model.oval;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * Created by huixiangdou on 2017/7/7.
 */
public class OvalP {
    @NotNull
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
