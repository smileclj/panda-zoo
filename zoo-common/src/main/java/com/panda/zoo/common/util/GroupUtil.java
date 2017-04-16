package com.panda.zoo.common.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huixiangdou on 2017/4/14.
 * 分组工具类
 */
public class GroupUtil {

    /**
     * 分组
     *
     * @param originalList 分组前的列表
     * @param limit        每组的最大限制数量
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> group(List<T> originalList, int limit) {
        if (limit == 0) {
            throw new IllegalArgumentException("limit must be greater than zero");
        }
        if (originalList.size() == 0) {
            throw new IllegalArgumentException("originalList is not empty");
        }
        List<List<T>> groups = new ArrayList<>(originalList.size());
        int groupNum = (int) Math.ceil(originalList.size() / (double) limit);
        for (int i = 0; i < groupNum; i++) {
            int start = i * limit;
            int end = start + limit;
            groups.add(originalList.subList(start, end > originalList.size() ? originalList.size() : end));
        }
        return groups;
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(new Integer[]{1, 2, 3, 4, 5});
        int limit = 2;
        List<List<Integer>> groups = GroupUtil.group(list, limit);
    }
}
