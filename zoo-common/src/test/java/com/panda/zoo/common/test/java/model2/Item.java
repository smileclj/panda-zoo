package com.panda.zoo.common.test.java.model2;

import java.io.Serializable;

/**
 * @author huixiangdou
 * @date 2018/9/24
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 4096298775804609512L;
    private String name = "红烧肉";
    private double price = 0.5d;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
