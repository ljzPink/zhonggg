package com.zhonggg.commonUtils;

public enum ResultEnum {
    LOGIN_ERROR(-1, "登录失效"),
    JURISDICTION_ERROR(-2, "没有权限"),
    UNKNOWN_ERROR(1, "运行异常"),
    SUCCESS(0, "操作成功");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
