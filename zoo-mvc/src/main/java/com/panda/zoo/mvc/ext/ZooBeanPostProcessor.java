package com.panda.zoo.mvc.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by huixiangdou on 2017/4/24.
 * spring bean初始化扩展点，spring aop正是利用了这一特性
 */
@Component
public class ZooBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "-----postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "-----postProcessAfterInitialization");
        return bean;
    }
}
