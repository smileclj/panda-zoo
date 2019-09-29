package com.panda.zoo.spring.boot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huixiangdou
 * @date 2019/9/29
 */
@Data
public class UserDto implements Serializable{
    private Long id;
    private String name;
}
