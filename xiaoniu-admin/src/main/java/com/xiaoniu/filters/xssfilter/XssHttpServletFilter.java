package com.xiaoniu.filters.xssfilter;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XssHttpServletFilter extends OncePerRequestFilter {
    private Logger log = Logger.getLogger(XssHttpServletFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) httpServletRequest);
            filterChain.doFilter(xssRequest, httpServletResponse);
        } catch (Exception e) {
            log.error("Xss过滤器，包装request对象失败");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
