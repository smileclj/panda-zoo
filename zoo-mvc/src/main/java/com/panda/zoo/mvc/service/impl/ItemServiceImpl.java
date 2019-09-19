package com.panda.zoo.mvc.service.impl;

import com.panda.zoo.mvc.service.IItemService;
import com.panda.zoo.mvc.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huixiangdou on 2017/4/24.
 */
@Service
public class ItemServiceImpl implements IItemService {
    @Resource
    private IUserService userService;

    @Override
    public void save() {
        System.out.println("save");
    }
}
