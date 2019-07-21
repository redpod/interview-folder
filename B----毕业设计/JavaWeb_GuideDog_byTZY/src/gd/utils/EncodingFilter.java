package gd.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "EncodingFilter")
@WebFilter("/*")
public class EncodingFilter implements Filter
{
    public void destroy()
    {
        System.out.println("中文编码验证过滤器销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        req.setCharacterEncoding("utf-8");
        System.out.println(((HttpServletRequest) req).getRequestURI());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {
        System.out.println("中文编码过滤器初始化");
    }

}