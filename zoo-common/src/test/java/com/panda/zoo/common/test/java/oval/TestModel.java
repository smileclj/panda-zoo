package com.panda.zoo.common.test.java.oval;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.NotNull;

/**
 * @author huixiangdou
 * @date 2018/9/25
 */
public class TestModel {

    @NotNull(message = "age不能为null",errorCode = "MULTI_BSA")
    private Integer age;

    @Digits(maxFraction = 2)
    private Double price;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
