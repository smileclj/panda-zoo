package com.panda.zoo.common.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author huixiangdou
 * @date 2018/12/6
 */
public class CollectionUtilsTest {
    @Test
    public void retainAll() {
        List<Integer> list1 = Lists.newArrayList(1, 2);
        List<Integer> list2 = Lists.newArrayList(2, 3);

        System.out.println(JSON.toJSONString(CollectionUtils.retainAll(list1,list2)));
    }
}
