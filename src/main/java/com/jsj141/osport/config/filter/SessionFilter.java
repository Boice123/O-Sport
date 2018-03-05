package com.jsj141.osport.config.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, accept, content-type, xxxx");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session =request.getSession();
            System.out.println("拦截器中的session的id是====" + session.getId());
            filterChain.doFilter(req, resp);
        } catch (ServletException sx) {
            System.out.print("sx" + sx);
        } catch (IOException iox) {
            System.out.print("iox" + iox);
        }
    }

    @Override
    public void destroy() {
    }
}
