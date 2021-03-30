package com.madao.xss.filter;

import com.madao.xss.function.ReciverFun;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class AllFilter implements Filter {

    @Autowired
    private ReciverFun reciverFun;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();


        //不过滤名单
        String[] notFilterPage = {"/api","/static","/websocket","/home","/login.html","/favicon.ico"};

        if(beaginWith(uri,notFilterPage)) {

            String method = request.getMethod();

            //GET请求
            if(method.equals("GET")) {

                //log.info(method +" 1 "+ uri);
                String methodData = method +" "+ uri;
                String data = request.getQueryString();
                //log.info(data);
                reciverFun.getRecive(request,data,methodData);

            }
            //POST请求
            if(method.equals("POST")) {

                //log.info(method +" 2 "+ uri);
                String methodData = method +" "+ uri;
                BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String str = "";
                String wholeStr = "";   //post body

                while ((str = reader.readLine())!=null) {
                    wholeStr += str;
                }
                //log.info(wholeStr);

                reciverFun.getRecive(request,wholeStr,methodData);

            }


        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    private boolean beaginWith(String currentPage, String[] notFilterPage) {
        boolean result = true;
        for (String page : notFilterPage) {
            if(StringUtils.startsWithIgnoreCase(currentPage,page))
                result = false;   //为不过滤页面时返回 false,,过滤页面返回true
        }
        return result;

    }


}
