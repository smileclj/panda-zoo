/*
 * @(#)SpringExceptionInterceptor.java Created on 2015年1月19日 Copyright (c) 2015 ZDSoft Networks, Inc. All rights
 * reserved. $Id$
 */
package com.panda.zoo.mvc.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不必在Controller中对异常进行处理，抛出即可，由此异常解析器统一控制。<br>
 * ajax请求（有@ResponseBody的Controller）发生错误，输出JSON。<br>
 * 页面请求（无@ResponseBody的Controller）发生错误，输出错误页面。<br>
 * 需要与AnnotationMethodHandlerAdapter使用同一个messageConverters<br>
 * Controller中需要有专门处理异常的方法。
 */
public class ZooExceptionResolver extends ExceptionHandlerExceptionResolver {
    private static final String defaultErrorView = "/page/404";

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
                                                           HandlerMethod handlerMethod, Exception e) {
        System.out.println("ZooExceptionResolver");
        return null;
    }
}
