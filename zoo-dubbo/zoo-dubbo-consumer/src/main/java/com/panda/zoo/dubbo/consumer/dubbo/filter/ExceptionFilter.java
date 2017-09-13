package com.panda.zoo.dubbo.consumer.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.io.IOException;

/**
 * Created by huixiangdou on 2017/2/23.
 */
@Activate(group = {Constants.CONSUMER})
public class ExceptionFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("interfaceName:" + invoker.getInterface().getName());
        System.out.println("url:" + invoker.getUrl());
        System.out.println("methodName:" + invocation.getMethodName());
        try {
            System.out.println("arguments:" + JSON.json(invocation.getArguments()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        com.panda.zoo.dubbo.provider.dto.Result result = new com.panda.zoo.dubbo.provider.dto.Result();
        result.setSuccess(false);
        try {
            Result dubboResult = invoker.invoke(invocation);
            if (dubboResult.hasException() && GenericService.class != invoker.getInterface()) {
                Throwable t = dubboResult.getException();
                result.setMessage("业务1异常!");
                return new RpcResult(result);
            }
            return dubboResult;
        } catch (RpcException e) {
            result.setMessage("服务器1异常!");
            return new RpcResult(result);
        }
    }
}
