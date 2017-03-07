package com.panda.zoo.common.test.json;

import com.alibaba.fastjson.JSON;
import com.panda.zoo.common.test.json.model.User;
import org.junit.Test;

/**
 * Created by huixiangdou on 2017/3/1.
 */
public class JsonTest {
    /**
     * 测试反序列化新增字段
     */
    @Test
    public void addNewField() {
        String userJsonStr = "{\"id\":1}";
        User user = JSON.parseObject(userJsonStr, User.class);
    }
}
