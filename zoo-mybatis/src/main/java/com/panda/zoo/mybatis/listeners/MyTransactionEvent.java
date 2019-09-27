package com.panda.zoo.mybatis.listeners;

import org.springframework.context.ApplicationEvent;

import java.util.HashMap;

/**
 * @Author: xiaoji
 * @Date: create on 2018/7/3
 * @Describle:
 */
public class MyTransactionEvent extends ApplicationEvent {
    private String tag;
    private String id;

    public MyTransactionEvent(String tag, HashMap<String, String> dataMap, String id, Object source) {
        super(source);
        this.tag = tag;
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

