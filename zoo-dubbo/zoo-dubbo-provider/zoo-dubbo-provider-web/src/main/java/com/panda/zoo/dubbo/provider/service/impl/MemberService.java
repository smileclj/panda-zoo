package com.panda.zoo.dubbo.provider.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.collect.Lists;
import com.panda.zoo.dubbo.provider.dto.MemberDto;
import com.panda.zoo.dubbo.provider.dto.Result;
import com.panda.zoo.dubbo.provider.service.IMemberService;
import com.panda.zoo.dubbo.provider.service.internal.IAsyncTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by huixiangdou on 2017/2/23.
 */
@Service
public class MemberService implements IMemberService {
    @Resource
    private IAsyncTestService asyncTestService;

    @Override
    public Result<MemberDto> getMember(String id) {
        Result<MemberDto> result = new Result<>();
        MemberDto memberDto = new MemberDto();
        memberDto.setId("1");
        memberDto.setName("小明");
        memberDto.setSex(1);
        memberDto.setPhone("11122223333");
        result.setModel(memberDto);
        System.out.println(1 / 0);
        return result;
    }

    @Override
    public Result<MemberDto> getMember(@NotNull(message = "id不能为空") String id, @NotNull(message = "name不能为空") String name) {
        return getMember(id);
    }

    @Override
    public Result testRpcContext(String id) {
        RpcContext context = RpcContext.getContext();
        System.out.println("getLocalAddressString->" + context.getLocalAddressString());
        System.out.println("getRemoteAddressString->" + context.getRemoteAddressString());
        System.out.println(context.getArguments());
        return null;
    }

    @Override
    public Result<String> testAsync() {
        List<Future> futureList = Lists.newArrayList();
        StringBuilder sb = new StringBuilder();
        futureList.add(asyncTestService.async1());
        futureList.add(asyncTestService.async2());
        futureList.forEach(future -> {
            try {
                if (future.isDone()) {
                    sb.append(future.get());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        Result result = new Result();
        result.setModel(sb.toString());
        return result;
    }

    @Override
    public Result testAsyncVoid() {
        asyncTestService.async3();
        return new Result();
    }

    @Override
    public Result<String> testAsyncTime() {
        Future<String> future = asyncTestService.async4();
        String r = "";
        try {
            r = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setModel(r);
        return result;
    }
}
