package com.panda.zoo.mybatis.listeners;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @Author: xiaoji
 * @Date: create on 2018/7/3
 * @Describle:
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class MyTransactionListener {

    @Async("transactionalMqEventExecutor")
    @TransactionalEventListener(fallbackExecution = true)
    public void onApplicationEvent(MyTransactionEvent event) {

    }
}