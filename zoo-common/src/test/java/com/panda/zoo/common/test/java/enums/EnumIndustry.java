package com.panda.zoo.common.test.java.enums;

/**
 * 行业类型
 *
 * @author huixiangdou
 * @date 2017/11/15
 */
public enum EnumIndustry {
    REPAST(0, "餐饮"), RETAIL(3, "新零售"), COMMON(-1, "通用");

    private int code;
    private String name;

    EnumIndustry(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static EnumIndustry codeOf(int industry) {
        for (EnumIndustry e : EnumIndustry.values()) {
            if (e.getCode() == industry) {
                return e;
            }
        }
        return null;
    }
}
