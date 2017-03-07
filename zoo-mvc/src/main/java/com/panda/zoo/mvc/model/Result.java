package com.panda.zoo.mvc.model;

import java.io.Serializable;

/**
 * Created by huixiangdou on 2017/3/7.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 8435733632047549713L;
    private boolean success = true;
    private String errCode; //失败时返回
    private String errMsg;  //失败时返回
    private T data;
    private int total;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
