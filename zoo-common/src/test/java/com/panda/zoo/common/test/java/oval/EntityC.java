package com.panda.zoo.common.test.java.oval;

import com.panda.zoo.common.test.java.hibernate.EntityP;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * Created by huixiangdou on 2017/7/7.
 */
public class EntityC extends EntityP {
    @NotBlank
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
