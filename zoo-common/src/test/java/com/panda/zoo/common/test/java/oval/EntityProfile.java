package com.panda.zoo.common.test.java.oval;

import net.sf.oval.constraint.NotNull;

/**
 * @author huixiangdou
 * @date 2018/7/10
 */
public class EntityProfile {
    @NotNull(profiles = {Profile.INSERT, Profile.UPDATE})
    private String id;
    @NotNull(profiles = {Profile.INSERT})
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
