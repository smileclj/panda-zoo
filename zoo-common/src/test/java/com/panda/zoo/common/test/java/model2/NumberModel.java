package com.panda.zoo.common.test.java.model2;

import java.io.Serializable;

/**
 * @author huixiangdou
 * @date 2018/12/24
 */
public class NumberModel implements Serializable{
    private Double d;
    private Long l;
    private Integer i;

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }
}
