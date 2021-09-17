package com.zkzy.portal.common.web.fliter;


import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yupc on 2017/6/6.
 */
public class hello implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.print("hello 初始化完成");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.print("执行了");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
