package com.panda.zoo.mvc.model;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * Created by huixiangdou on 2017/3/7.
 */
public class UserVo implements Serializable,InitializingBean {
    private static final long serialVersionUID = 6387235471376063659L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after property");
    }
}
