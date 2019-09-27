package com.panda.zoo.mybatis.dao.impl;

import com.panda.zoo.mybatis.dao.IUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huixiangdou
 * @date 2019/8/30
 */
@Service
public class UserDaoImpl implements IUserDao {
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser() {
        System.out.println("UserDaoImpl addUser");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteUser() {
        System.out.println("UserDaoImpl deleteUser");
    }
}
