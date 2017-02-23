package com.panda.zoo.dubbo.consumer.test;

import com.alibaba.dubbo.rpc.RpcException;
import com.panda.zoo.dubbo.provider.dto.MemberDto;
import com.panda.zoo.dubbo.provider.dto.Result;
import com.panda.zoo.dubbo.provider.service.IMemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by huixiangdou on 2017/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DubboTest {
    @Resource
    private IMemberService memberService;

    @Test
    public void getMember() {
        try {
            Result<MemberDto> result = memberService.getMember("1", "1");
            Assert.assertTrue(result.isSuccess());
        } catch (Exception e) {
            if (e instanceof RpcException) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
                    Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
                }
            }
        }
    }
}
