package com.panda.zoo.mvc.controller;

import com.panda.zoo.mvc.model.Result;
import com.panda.zoo.mvc.model.UserVo;
import com.panda.zoo.mvc.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by huixiangdou on 2017/3/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/getUserById")
    @ResponseBody
    public Result<UserVo> getUserById(Integer id, HttpServletRequest req) {
        Result<UserVo> result = new Result<>();
        UserVo userVo = new UserVo();
        userVo.setId(id);
        userVo.setName("小明");
        result.setData(userVo);
        userService.testAop();
        return result;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Result test(@RequestBody UserVo body) {
        System.out.println(body);
        Result result = new Result();
        result.setData(body);
        return result;
    }

    @RequestMapping("/test1")
    @ResponseBody
    public ModelAndView test1() {
        return new ModelAndView();
    }

    @RequestMapping("/testReq")
    @ResponseBody
    public Result testReq(HttpServletRequest req) {
        Map<String, String[]> map = req.getParameterMap();
        Result result = new Result();
        return result;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam(required = false) CommonsMultipartFile file) {
        Result result = new Result();
        return result;
    }

    @RequestMapping("/exception")
    @ResponseBody
    public Result exception(boolean printEx) {
        Result result = new Result();
        if (printEx) {
            throw new IllegalArgumentException("非法参数!");
        }
        return result;
    }
}
