package com.sud.common.filter;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 */
@WebFilter(filterName="myFilter",urlPatterns="/note/*")
public class NoteFilter implements Filter {

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



        String contextPath = httpRequest.getContextPath();

        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");
        if(username !=null && !username.isEmpty()){
            chain.doFilter(request, response);
        }else {
            String uri = httpRequest.getRequestURI();
//            for(String s : uriFilter){
//                if(uri.indexOf(s)>=0){
//                    chain.doFilter(request, response);
//                    return;
//                }
//            }
//            httpRequest.getRequestDispatcher("/login.html").forward(httpRequest,httpResponse);
//            httpResponse.sendRedirect("/login.html");
            OutputStream out = httpResponse.getOutputStream();
            httpResponse.setHeader("Content-type", "text/html;charset=utf-8");
            JSONObject result = new JSONObject();
            result.put("flag",false);
            result.put("message","你还没有登录");
            out.write(result.toString().getBytes());
            out.flush();
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

}
