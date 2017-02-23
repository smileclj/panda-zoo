package com.panda.zoo.dubbo.provider.service.impl;

import com.panda.zoo.dubbo.provider.dto.MemberDto;
import com.panda.zoo.dubbo.provider.dto.Result;
import com.panda.zoo.dubbo.provider.service.IMemberService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by huixiangdou on 2017/2/23.
 */
@Service
public class MemberService implements IMemberService {
    @Override
    public Result<MemberDto> getMember(String id) {
        Result<MemberDto> result = new Result<>();
        MemberDto memberDto = new MemberDto();
        memberDto.setId("1");
        memberDto.setName("小明");
        memberDto.setSex(1);
        memberDto.setPhone("11122223333");
        result.setModel(memberDto);
        return result;
    }

    @Override
    public Result<MemberDto> getMember(@NotNull(message = "id不能为空") String id, @NotNull(message = "name不能为空") String name) {
        return getMember(id);
    }
}
