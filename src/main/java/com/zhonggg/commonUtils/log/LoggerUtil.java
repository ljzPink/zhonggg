package com.zhonggg.commonUtils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志打印工具
 *
 * @author wangxiaolong
 * @date 2019/6/13.
 */
public class LoggerUtil {
    public final static Logger LOGGER = LoggerFactory.getLogger("");

    public final static String LOGGER_BEGIN = "[{}] Begin URI: {}";
    public final static String LOGGER_END = "[{}] End";
    public final static String LOGGER_ERROR = "[{}] Error:";
    public final static String LOGGER_WARN = "[{}] Warn: {}";
    public final static String LOGGER_BEGIN_MESSAGE = "[{}]  URI: {}\nRequestBody: {}";
    public final static String LOGGER_END_MESSAGE = "[{}] End ResponseBody: {code:{},msg:{}}";

}
