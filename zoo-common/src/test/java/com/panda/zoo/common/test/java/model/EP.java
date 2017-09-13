package com.panda.zoo.common.test.java.model;

/**
 * Created by huixiangdou on 2017/7/14.
 */
public class EP {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EP ep = (EP) o;

        return id.equals(ep.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
