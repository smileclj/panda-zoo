package com.panda.zoo.mvc.controller;

import com.panda.zoo.mvc.model.Result;
import com.panda.zoo.mvc.model.UserVo;
import com.panda.zoo.mvc.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        userService.test(req);
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

    @RequestMapping("/testReq")
    @ResponseBody
    public Result testReq(HttpServletRequest req) {
        Map<String, String[]> map = req.getParameterMap();
        Result result = new Result();
        return result;
    }
}
