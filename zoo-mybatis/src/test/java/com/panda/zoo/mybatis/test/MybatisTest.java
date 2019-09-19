package com.panda.zoo.mybatis.test;

import com.panda.zoo.mybatis.mapper.StudentMapper;
import com.panda.zoo.mybatis.model.Student;
import com.panda.zoo.mybatis.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author huixiangdou
 * @date 2017/11/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MybatisTest {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private IUserService userService;

    @Test
    public void test1() {
        Student student = studentMapper.selectByPrimaryKey("1");

        student.setAge(111);
        studentMapper.updateByPrimaryKeySelective(student, true);
    }

    @Test
    public void test2(){
        userService.addUser();
    }
}