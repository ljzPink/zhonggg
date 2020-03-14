package com.zhonggg.commonUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-03-05 14:26
 */
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
