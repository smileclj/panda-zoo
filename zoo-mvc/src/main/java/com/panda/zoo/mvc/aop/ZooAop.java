package com.panda.zoo.mvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by huixiangdou on 2017/4/23.
 */
@Aspect
@Component
public class ZooAop {

    @Pointcut("execution(* com.panda.zoo.mvc.service.impl.UserServiceImpl.testAop(..))")
    public void service() {

    }

//    @Before("service()")
//    public void before() {
//        System.out.println("zooAop before");
//    }
//
//    @After("service()")
//    public void after() {
//        System.out.println("zooAop after");
//    }

    /**
     * around切点和before、after同时存在时，只有around会生效
     */
    @Around("service()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("zooAop around before");
        Object result = pjp.proceed();
        System.out.println("zooAop around after");
    }

    @AfterReturning("service()")
    public void afterReturning() {
        System.out.println("zooAop afterReturning");
    }

    @AfterThrowing("service()")
    public void afterThrowing() {
        System.out.println("zooAop afterThrowing");
    }
}
