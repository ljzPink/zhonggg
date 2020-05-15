package com.zhonggg.commonUtils.log;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wangxiaolong
 * @date 2019/12/6 14:17
 */
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (RequestMethod.POST.toString().equalsIgnoreCase(httpServletRequest.getMethod())) {
            RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
            if (requestWrapper != null) {
                servletRequest = requestWrapper;
            }
        } else {
            String operateInfo = httpServletRequest.getParameter("operateInfo");
            if (operateInfo != null && operateInfo.length() > 0) {
                servletRequest.setAttribute("operateInfo", operateInfo);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
