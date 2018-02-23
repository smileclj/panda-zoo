package com.panda.zoo.dubbo.provider.service;

import com.panda.zoo.dubbo.provider.dto.MemberDto;
import com.panda.zoo.dubbo.provider.dto.Result;

import javax.validation.constraints.NotNull;

/**
 * Created by huixiangdou on 2017/2/23.
 * 会员服务
 */
public interface IMemberService {
    /**
     * 查询会员信息
     *
     * @param id
     * @return
     */
    Result<MemberDto> getMember(@NotNull(message = "id不能为空") String id);

    Result<MemberDto> getMember(@NotNull(message = "id不能为空") String id, @NotNull(message = "name不能为空") String name);

    Result testRpcContext(String id);
}
