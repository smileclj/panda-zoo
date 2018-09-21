package com.panda.zoo.common.test.java.model2;

import java.io.Serializable;
import java.util.List;

/**
 * 分类列表和商品列表互斥
 * 即有子类的分类下不允许有商品
 *
 * @author huixiangdou
 * @date 2018/8/28
 */
public class CategoryEntry<T extends ItemEntry> implements Serializable {
    private static final long serialVersionUID = 706601405587461567L;
    /**
     * 分类id
     */
    private String categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类列表
     */
    private List<CategoryEntry<T>> categoryList;
    /**
     * 商品列表
     */
    private List<T> itemList;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryEntry<T>> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryEntry<T>> categoryList) {
        this.categoryList = categoryList;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }
}
