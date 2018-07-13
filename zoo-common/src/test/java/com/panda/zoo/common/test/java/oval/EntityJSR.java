package com.panda.zoo.common.test.java.oval;

import javax.validation.constraints.NotNull;

/**
 * @author huixiangdou
 * @date 2018/7/13
 */
public class EntityJSR {
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
