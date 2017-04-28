package com.panda.zoo.mvc.service.impl;

import com.panda.zoo.mvc.service.IItemService;
import org.springframework.stereotype.Service;

/**
 * Created by huixiangdou on 2017/4/24.
 */
@Service
public class ItemServiceImpl implements IItemService {
    @Override
    public void save() {
        System.out.println("save");
    }
}
