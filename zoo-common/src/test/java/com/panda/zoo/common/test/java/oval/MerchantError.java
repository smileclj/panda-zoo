package com.panda.zoo.common.test.java.oval;

/**
 * @author huixiangdou
 * @date 2018/11/30
 */
public enum MerchantError {
    SYSTEM_ERROR("系统异常", ""),
    SYSTEM_BUSY("系统繁忙", ""),
    INVALID_PARAMETER("参数错误", ""),
    INVALID_NO_DATA("操作的数据不存在", ""),
    INVALID_TRIGGER_CODE("已经存在触发机制和奖励规则一样的任务", ""),
    INVALID_TASK_NAME("已经存在相同名称的任务", ""),
    INVALID_TASK_RANGE("过期的任务不能启动", ""),
    INVALID_TASK_SIDE("非法的任务主体", ""),
    INVALID_AWARD_TYPE("非法的奖励类型", "");


    private String errMsg;
    private String multiCode;

    MerchantError(String errMsg, String multiCode) {
        this.errMsg = errMsg;
        this.multiCode = multiCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getMultiCode() {
        return multiCode;
    }

    public String getMsg() {
        return getErrMsg();
    }
}
