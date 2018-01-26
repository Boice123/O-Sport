package com.jsj141.osport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一处理
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        try {
            //TODO 增加一些判断决定返回什么数据
            response.sendRedirect("/500.html");
        } catch (Exception e) {
            logger.error("统一异常处理:", e);
        }
        return null;
    }

}
