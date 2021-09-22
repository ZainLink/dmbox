package com.zkzy.portal.common.web.fliter;

import com.zkzy.portal.common.web.security.XssHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Thinkpad-W530 on 2020/9/1.
 */
public class CrosXssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XssHttpServletRequestWrapper(
                (HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {

    }
}
