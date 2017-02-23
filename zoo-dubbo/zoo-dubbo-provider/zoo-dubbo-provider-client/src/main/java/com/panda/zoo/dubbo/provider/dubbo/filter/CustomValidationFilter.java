package com.panda.zoo.dubbo.provider.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.validation.Validation;
import com.alibaba.dubbo.validation.Validator;
import com.google.common.collect.Lists;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by huixiangdou on 2017/2/23.
 */
@Activate(group = {Constants.CONSUMER, Constants.PROVIDER}, value = Constants.VALIDATION_KEY, order = 10000)
public class CustomValidationFilter implements Filter {
    private Validation validation;

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        if (validation != null && !invocation.getMethodName().startsWith("$")
//                && ConfigUtils.isNotEmpty(invoker.getUrl().getMethodParameter(invocation.getMethodName(), Constants.VALIDATION_KEY))) {
            try {
                Validator validator = validation.getValidator(invoker.getUrl());
                if (validator != null) {
                    validator.validate(invocation.getMethodName(), invocation.getParameterTypes(), invocation.getArguments());
                }
            } catch (RpcException e) {
                throw e;
            } catch (Throwable t) {
                com.panda.zoo.dubbo.provider.dto.Result result = new com.panda.zoo.dubbo.provider.dto.Result();
                result.setSuccess(false);
                ConstraintViolationException constraintViolationException = (ConstraintViolationException) t;
                Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
                if (constraintViolationSet.size() > 0) {
                    //此处为set实现为hashSet，因此不能保证顺序
                    ConstraintViolation constraintViolation = Lists.newArrayList(constraintViolationSet).get(0);
                    result.setMessage(constraintViolation.getMessageTemplate());
                }else{
                    result.setMessage("参数校验失败!");
                }
                return new RpcResult(result);
            }
//        }
        return invoker.invoke(invocation);
    }
}
