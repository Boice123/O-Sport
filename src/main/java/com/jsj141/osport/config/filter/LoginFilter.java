package com.jsj141.osport.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import com.iw86.base.RequestUtil;
import com.iw86.base.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/userlogin/*")
public class LoginFilter implements Filter {

    private List<String> passList = new ArrayList<>(Arrays.asList(new String[]{"userlogin/user/signin","userlogin/user/save"}));

    private boolean canPass(String path) {
        for (String url : passList) {
            if (StringUtil.isMatcher(url, path)) {
                return true;
            }
        }
        return false;
    }

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = request.getSession();
            String path = RequestUtil.getPath(request);
            if (canPass(path)) {
                filterChain.doFilter(req, resp);
                return;
            }
            if (session.getAttribute("loginUser") == null) {
                response.sendRedirect("/user/signin");
                return;
            }
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
