package com.sud.order.filter;

import com.sud.common.WebContent;
import com.sud.common.dto.BaseRes;
import com.sud.user.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(filterName="myFilter",urlPatterns="/order/*")
public class OrderFilter implements Filter {

    private String[] uriFilter = {};

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

         HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");
        if(username !=null && !username.isEmpty()){
            WebContent.getInstance().getUserThreadLocal().set((User) session.getAttribute("user"));
            chain.doFilter(request, response);
        }else {
            OutputStream out = httpResponse.getOutputStream();
            httpResponse.setHeader("Content-type", "text/html;charset=utf-8");
            BaseRes baseRes = new BaseRes();
            baseRes.setMessage("你还没有登录");
            out.write("4".getBytes());

            out.flush();
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

}
