package com.panda.zoo.common.test.java.model2;

import java.io.Serializable;

/**
 * @author huixiangdou
 * @date 2018/8/28
 */
public class ItemEntry implements Serializable {
    private static final long serialVersionUID = 9199310007298131617L;
    private String itemId;
    private String itemName;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
