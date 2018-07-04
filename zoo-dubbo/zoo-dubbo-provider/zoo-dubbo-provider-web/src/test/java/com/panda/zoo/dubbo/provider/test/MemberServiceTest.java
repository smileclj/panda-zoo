package com.panda.zoo.dubbo.provider.test;

import com.panda.zoo.dubbo.provider.dto.Result;
import com.panda.zoo.dubbo.provider.service.IMemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author huixiangdou
 * @date 2018/7/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceTest {
    @Resource
    private IMemberService memberService;

    @Test
    public void testAsync() {
        Result<String> result = memberService.testAsync();
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testAsyncVoid() {
        Result result = memberService.testAsyncVoid();
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testAsyncTime() {
        Result<String> result = memberService.testAsyncTime();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result.isSuccess());
    }
}
