package com.panda.zoo.common.test.java.model;

/**
 * Created by huixiangdou on 2017/7/12.
 */
public class CacheKey {
    private StringBuilder sb = new StringBuilder("a");
    private static final String SPLITTER = ":";

    private String entityId;
    private Long multipleMenuId;

    public CacheKey(String entityId) {
        sb.append(SPLITTER).append(entityId);
    }

    public CacheKey(String entityId, Long multipleMenuId) {
        sb.append(SPLITTER).append(entityId).append(multipleMenuId);
    }

    public String getKey() {
        return sb.toString();
    }
}
