package com.zhonggg.commonUtils.exceptions;

/**
 * 自定义全局异常类
 *
 * @author jiangwenbo
 * @date 2019/6/17.
 */

public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
