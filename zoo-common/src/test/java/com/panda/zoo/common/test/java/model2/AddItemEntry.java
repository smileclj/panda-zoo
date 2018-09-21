package com.panda.zoo.common.test.java.model2;

/**
 * @author huixiangdou
 * @date 2018/8/28
 */
public class AddItemEntry extends ItemEntry {
    private static final long serialVersionUID = 4670278909104713565L;
    /**
     * 是否已选
     */
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
