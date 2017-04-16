package com.panda.zoo.common.test.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panda.zoo.common.test.java.model.EmptyField;
import org.junit.Test;

/**
 * Created by huixiangdou on 2017/4/15.
 */
public class JackSonTest {
    @Test
    public void serialize() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(new EmptyField()));
    }
}
