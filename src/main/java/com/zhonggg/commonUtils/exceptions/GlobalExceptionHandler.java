package com.zhonggg.commonUtils.exceptions;

import com.zhonggg.commonUtils.Result;
import com.zhonggg.commonUtils.ResultEnum;
import com.zhonggg.commonUtils.ResultUtil;
import com.zhonggg.commonUtils.log.LoggerUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author jiangwenbo
 * @date 2019/6/17.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(HttpServletRequest request, Exception e) {
        String message;
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            message = globalException.getMessage();
        } else if (e instanceof IncorrectCredentialsException) {
            message = "用户名密码错误";
        } else if (e instanceof AuthenticationException) {
            message = e.getMessage();
        } else if (e instanceof DuplicateKeyException) {
            message = "已有同名数据，请勿重复插入";
        } else if (e instanceof HttpMessageNotReadableException) {
            message = "Json参数异常";
            LoggerUtil.LOGGER.error(request.getRequestURI());
            LoggerUtil.LOGGER.error(LoggerUtil.LOGGER_ERROR, request.getRemoteHost(), e);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            message = ResultEnum.UNKNOWN_ERROR.getMsg();
            LoggerUtil.LOGGER.error(request.getRequestURI());
            LoggerUtil.LOGGER.error(LoggerUtil.LOGGER_ERROR, request.getRemoteHost(), e);
        } else if (e instanceof MaxUploadSizeExceededException) {
            message = "上传数据不允许超过10mb";
        } else if (e instanceof UnknownSessionException) {
            message = ResultEnum.LOGIN_ERROR.getMsg();
        } else {
            message = ResultEnum.UNKNOWN_ERROR.getMsg();
            LoggerUtil.LOGGER.error(LoggerUtil.LOGGER_ERROR, request.getRemoteHost(), e);
        }
        return ResultUtil.error(message);
    }
}
