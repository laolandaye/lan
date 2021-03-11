package com.sxsh.config.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// /* 表示全部拦截
@WebFilter(filterName = "corsFilter",urlPatterns = "/*")
public class CorsFilter implements Filter {


    //初始化调用的方法
    //当服务器 被启动的时候，调用
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    //拦截的方法
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //解决跨域的问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept,"
                +"content-Type,origin,x-requested-with,content-type,accept,authorization,token,id,X-Custom-Header,X-Cookie,Connection,User-Agent,*");
        response.setHeader("Access-Control-Request-Method", "PUT,GET,POST,DELETE,OPTIONS");

        filterChain.doFilter(request, response);


    }

    //销毁时候调用的方法
    public void destroy() {
    }
}