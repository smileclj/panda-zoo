package com.panda.zoo.mvc.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huixiangdou on 2017/3/22.
 */
public interface IUserService {
    void test(HttpServletRequest req);

    void testAop();

    void testNoAop();
}
