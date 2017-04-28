package com.panda.zoo.mvc.test;

import com.panda.zoo.mvc.service.IItemService;
import com.panda.zoo.mvc.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by huixiangdou on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ServiceTest {
    @Resource
    private IUserService userService;
    @Resource
    private IItemService itemService;

    @Test
    public void testAop() {
        userService.testAop();
    }

    @Test
    public void testNoAop() {
        userService.testNoAop();
    }

    @Test
    public void save() {
        itemService.save();
    }
}
