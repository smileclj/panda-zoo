package com.panda.zoo.mvc.controller;

import com.panda.zoo.mvc.model.Result;
import com.panda.zoo.mvc.model.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huixiangdou on 2017/3/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserById")
    @ResponseBody
    public Result<UserVo> getUserById(Integer id) {
        Result<UserVo> result = new Result<>();
        UserVo userVo = new UserVo();
        userVo.setId(id);
        userVo.setName("小明");
        result.setData(userVo);
        return result;
    }
}
