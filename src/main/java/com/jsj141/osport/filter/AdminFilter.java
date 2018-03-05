package com.jsj141.osport.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * session保持不变过滤器
 */

@WebFilter(filterName = "adminFilter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        // TODO 过滤逻辑
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}