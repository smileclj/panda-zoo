package com.panda.zoo.dubbo.provider.service.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * Created by huixiangdou on 2017/2/23.
 */
public class CustomExceptionFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        com.panda.zoo.dubbo.provider.dto.Result result = new com.panda.zoo.dubbo.provider.dto.Result();
        result.setSuccess(false);
        try {
            Result dubboResult = invoker.invoke(invocation);
            if (dubboResult.hasException() && GenericService.class != invoker.getInterface()) {
                Throwable t = dubboResult.getException();
                result.setMessage("业务异常!");
                return new RpcResult(result);
            }
            return dubboResult;
        } catch (RpcException e) {
            result.setMessage("服务器异常!");
            return new RpcResult(result);
        }
    }
}
