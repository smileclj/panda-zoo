package com.panda.zoo.common.test.java.oval;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * @author huixiangdou
 * @date 2018/7/10
 */
public class EntityWhen {
    @Min(0)
    private Integer age;
    @NotNull(when = "groovy:_this.age > 1")
    private String address;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
