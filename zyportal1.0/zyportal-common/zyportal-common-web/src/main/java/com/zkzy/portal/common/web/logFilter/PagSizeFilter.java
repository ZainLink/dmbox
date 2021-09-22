package com.zkzy.portal.common.web.logFilter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Thinkpad-W530 on 2021/1/27.
 */
public class PagSizeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截最大条数
        String pageSize = servletRequest.getParameter("pageSize");
        ParameterRequestWrapper changeRequestWrapper = new ParameterRequestWrapper((HttpServletRequest) servletRequest);
        if (StringUtils.isNoneEmpty(pageSize)) {

            int size = 0;
            try {
                int a = Integer.parseInt(pageSize);
                if (a > 200) {
                    size = 200;
                } else {
                    size = a;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                size = 10;
            }
            changeRequestWrapper.addParameter("pageSize", size + "");
            //复写 HttpServletRequestWrapper

        }
        filterChain.doFilter(changeRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
