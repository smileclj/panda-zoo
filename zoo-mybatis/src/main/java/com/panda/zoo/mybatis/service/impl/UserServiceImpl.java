package com.panda.zoo.mybatis.service.impl;

import com.panda.zoo.mybatis.dao.IUserDao;
import com.panda.zoo.mybatis.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author huixiangdou
 * @date 2019/8/29
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser() {
        System.out.println("UserServiceImpl");
        userDao.addUser();
    }
}
