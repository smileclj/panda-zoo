package com.panda.zoo.common.test.java.model;

/**
 * Created by huixiangdou on 2017/4/26.
 */
public class Ha {
    private int id;
    private String name;
    private long time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ha ha = (Ha) o;

        if (id != ha.id) return false;
        if (time != ha.time) return false;
        return name != null ? name.equals(ha.name) : ha.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }
}
