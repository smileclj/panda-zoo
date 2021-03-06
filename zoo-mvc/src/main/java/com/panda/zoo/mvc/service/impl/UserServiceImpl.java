package com.panda.zoo.mvc.service.impl;

import com.panda.zoo.mvc.model.UserVo;
import com.panda.zoo.mvc.service.IItemService;
import com.panda.zoo.mvc.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huixiangdou on 2017/3/22.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IItemService itemService;

    @Override
    public void test(HttpServletRequest req) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
        UserVo userVo = webApplicationContext.getBean(UserVo.class);
        UserVo userVo1 = (UserVo) webApplicationContext.getBean("userVo");
    }

    @Override
    public void testAop() {
        System.out.println("testAop");
    }

    @Override
    public void testNoAop() {
        System.out.println("testNoAop");
    }
}
