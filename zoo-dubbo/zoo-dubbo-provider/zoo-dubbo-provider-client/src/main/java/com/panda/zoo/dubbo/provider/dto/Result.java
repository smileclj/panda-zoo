package com.panda.zoo.dubbo.provider.dto;

import java.io.Serializable;

/**
 * Created by huixiangdou on 2017/2/23.
 */
public class Result<T> implements Serializable{
    private static final long serialVersionUID = 5747556624538989779L;
    private boolean success = true;
    private String resultCode;
    private String message;
    private T model;
    private int total;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
