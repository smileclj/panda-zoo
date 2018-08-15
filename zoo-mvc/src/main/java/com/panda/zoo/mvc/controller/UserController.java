package com.panda.zoo.mvc.controller;

import com.panda.zoo.mvc.model.Result;
import com.panda.zoo.mvc.model.UserVo;
import com.panda.zoo.mvc.service.IUserService;
import com.panda.zoo.mvc.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    public Result<UserVo> getUserById(Integer id, HttpServletRequest req, HttpServletResponse res) {
//        req.getRequestDispatcher("").forward(req,res);
        Result<UserVo> result = new Result<>();
        UserVo userVo = new UserVo();
        userVo.setId(id);
        userVo.setName("小明");
        result.setData(userVo);
        userService.testAop();
        return result;
    }

    @RequestMapping("/testRequestBody")
    @ResponseBody
    public Result testRequestBody(@RequestBody UserVo body) {
        System.out.println(body);
        Result result = new Result();
        result.setData(body);
        return result;
    }

    @RequestMapping("/testWrapParam")
    @ResponseBody
    public Result testWrapParam(UserVo userVo) {
        Result result = new Result();
        result.setData(userVo);
        return result;
    }

    @RequestMapping("/testMv")
    @ResponseBody
    public ModelAndView testMv() {
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
    public Result upload(@RequestParam("file") CommonsMultipartFile file, String name) {
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getStorageDescription());

//        file
//        image/jpeg
//        longmao.jpg
//        at [/Users/chenlijiang/Library/Caches/IntelliJIdea2016.3/tomcat/Unnamed_zoo_2/work/Catalina/localhost/ROOT/upload_6608553a_c77c_40b4_a8e3_31290647a667_00000000.tmp]
        Result result = new Result();
        return result;
    }

    @RequestMapping("/multiUpload")
    @ResponseBody
    public Result multiUpload(@RequestParam("file") CommonsMultipartFile file, @RequestParam("file1") CommonsMultipartFile file1, String name) {
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
