package com.panda.zoo.common.test.copy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.panda.zoo.common.test.java.model2.*;
import org.dozer.DozerBeanMapper;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * @author huixiangdou
 * @date 2018/10/30
 */
public class DozerTest {
    private static DozerBeanMapper mapper = new DozerBeanMapper();

    static {
        mapper.setMappingFiles(Lists.newArrayList("tmp/dozer2.xml"));
    }

    @Test
    public void test1() {
        Same1 same1 = new Same1();
        same1.setId("1");
        Same2 same2 = mapper.map(same1, Same2.class);

        System.out.println(JSON.toJSONString(same2));

    }

    @Test
    public void test2() {
        ComplexModel complexModel = new ComplexModel();

        LocalDateTime localDateTime = LocalDateTime.parse("2015-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        complexModel.setD(Date.from(zonedDateTime.toInstant()));
        complexModel.setType(EnumType.GREEN);
        complexModel.setList(Lists.newArrayList("1"));
        Same1 same1 = new Same1();
        same1.setId("1");
        same1.setName("小明");
        complexModel.setSame1(same1);
        complexModel.setSame1List(Lists.newArrayList(same1));
        Map<String, Same1> map = Maps.newHashMap();
        map.put("a", same1);
        complexModel.setMap(map);

        Same1 same11 = new Same1();
        same11.setId("2");
        same11.setName("小明2");
        ComplexModel2 complexModel2 = new ComplexModel2();
        complexModel2.setSame1List(Lists.newArrayList(same11));
        complexModel2.setSame1(same11);

        Map<String, Same1> map2 = Maps.newHashMap();
        map.put("a", same11);
        complexModel2.setMap(map2);


        mapper.map(complexModel2,complexModel);
    }
}
