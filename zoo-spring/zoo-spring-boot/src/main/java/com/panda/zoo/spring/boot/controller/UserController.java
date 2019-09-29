package com.panda.zoo.spring.boot.controller;

import com.panda.zoo.spring.boot.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author huixiangdou
 * @date 2019/9/29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserById")
    public UserDto getUserById(@RequestParam(required = false) Long id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName("小明");
        return userDto;
    }
}
