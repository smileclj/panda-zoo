package com.panda.zoo.common.test.java.oval;

import net.sf.oval.constraint.NotNull;

/**
 * @author huixiangdou
 * @date 2018/7/6
 */
public class EntityD {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
