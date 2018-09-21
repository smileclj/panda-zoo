package com.panda.zoo.common.test.spring;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author huixiangdou
 * @date 2018/8/15
 */
public class SpringUtilTest {
    @Test
    public void tokenizeToStringArray() {
        String[] ss = StringUtils.tokenizeToStringArray("a.b\na.d", ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        System.out.println(JSON.toJSONString(ss));
    }
}
