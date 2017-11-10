package com.panda.zoo.common.test.java.model;

/**
 * @author huixiangdou
 * @date 2017/10/11
 */
public class OverChild extends OverParent {
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public static void main(String[] args) {
        OverChild overChild = new OverChild();
    }
}
