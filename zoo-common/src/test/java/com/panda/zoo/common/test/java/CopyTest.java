package com.panda.zoo.common.test.java;

import com.panda.zoo.common.test.java.model.Date1;
import com.panda.zoo.common.test.java.model.Date2;
import com.panda.zoo.common.test.java.model.copy.SexEnum;
import com.panda.zoo.common.test.java.model.copy.StudentBO;
import com.panda.zoo.common.test.java.model.copy.StudentDO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huixiangdou on 2017/5/18.
 */
public class CopyTest {
    @Test
    public void apacheBeanUtilDoToBo() throws Exception {
        StudentBO dest = new StudentBO();
        StudentDO orig = new StudentDO();
        orig.setId(1);
        orig.setName("小明");
        orig.setSex(1);
        PropertyUtils.copyProperties(dest, orig);
    }

    @Test
    public void apacheBeanUtilBoToDo() throws Exception {
        StudentBO orig = new StudentBO();
        StudentDO dest = new StudentDO();
        orig.setId(1);
        orig.setName("小明");
        orig.setSex(SexEnum.MALE);
        PropertyUtils.copyProperties(dest, orig);
    }

    @Test
    public void apacheBeanUtil2() throws Exception {
        StudentBO dest = new StudentBO();
        StudentDO orig = new StudentDO();
        orig.setId(1);
        orig.setName("小明");
        orig.setSex(1);
        BeanUtils.copyProperties(dest, orig);
    }

    @Test
    public void springBeanUtilDoToBo() throws Exception {
        StudentBO dest = new StudentBO();
        StudentDO orig = new StudentDO();
        orig.setId(1);
        orig.setName("小明");
        orig.setSex(1);
        org.springframework.beans.BeanUtils.copyProperties(orig,dest);
    }

    @Test
    public void springBeanUtilBoToDo() throws Exception {
        StudentBO orig = new StudentBO();
        StudentDO dest = new StudentDO();
        orig.setId(1);
        orig.setName("小明");
        orig.setSex(SexEnum.MALE);
        org.springframework.beans.BeanUtils.copyProperties(orig,dest);
    }

    @Test
    public void dateCopy() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Date1 d1 = new Date1();
        d1.setTime(new Date());

        Date2 d2 = new Date2();

        PropertyUtils.copyProperties(d2,d1);
    }

    @Test
    public void mapToString(){
        Map<String,String> map = new HashMap<>();
        map.put("1","2");

        System.out.println(map + "2");
    }
}
