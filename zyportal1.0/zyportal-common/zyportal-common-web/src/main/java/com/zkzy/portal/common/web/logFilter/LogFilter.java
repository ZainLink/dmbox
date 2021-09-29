package com.zkzy.portal.common.web.logFilter;

import com.zkzy.portal.common.web.util.IpUtils;
import com.zkzy.portal.common.web.util.WebUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFilter implements HandlerInterceptor {

    public static final String TOKEN_HEADER = "Authorization";


    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) {
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            return;
        }
        //ip
        String ip = IpUtils.getIpAddress(request);
        if (ip.split("\\.").length != 4) {
            return;
        }
        String username = null;
        try {
            username = WebUtils.getCurrentUser().getUsername();
        } catch (Exception e) {
            username = "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //请求时间
        String time = sdf.format(new Date());
        //访问路径
        String URI = request.getRequestURI();
        LOGGER.info((StringUtils.isNoneEmpty(username) ? username : "无权限") + "请求了接口" + URI + "时间：" + time);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
