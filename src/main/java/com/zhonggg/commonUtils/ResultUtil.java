package com.zhonggg.commonUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 生成result工具类
 * @author: lijinzhong
 * @create: 2020-03-05 14:29
 */
public class ResultUtil {
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result success(Integer code, String msg, Object obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static Result error(String msg) {
        return error(ResultEnum.UNKNOWN_ERROR.getCode(), msg);
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj instanceof Collection) {
            map.put("list", obj);
            obj = map;
        }
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(obj);
        return result;
    }
}
