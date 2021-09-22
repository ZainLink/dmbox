package com.zkzy.portal.common.web.logFilter;

import com.zkzy.portal.base.admin.api.entity.SystemLog;
import com.zkzy.portal.common.web.util.AuthUser;
import com.zkzy.portal.common.web.util.IpUtils;
import com.zkzy.portal.common.web.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogFilter implements HandlerInterceptor {

    public static final String TOKEN_HEADER = "Authorization";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) {
        String method = request.getMethod();
        LogDao logDao = null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return;
        }
        //ip
        String ip = IpUtils.getIpAddress(request);
        if (ip.split("\\.").length != 4) {
            return;
        }
        String username = null;
        String name = null;
        String authHeader = httpRequest.getHeader(TOKEN_HEADER);
        if (authHeader != null) {
            try {
                username = WebUtils.getCurrentUser() != null ? WebUtils.getCurrentUser().getUsername() : "";
            } catch (Exception e) {
                e.printStackTrace();
                username = "";
            }
            //获取请求参数
            String queryString = request.getQueryString();
            try {
                if (WebUtils.getCurrentUser() != null) {
                    AuthUser authUser = (AuthUser) WebUtils.getCurrentUser();
                    name = authUser != null ? authUser.getName() : "";
                } else {
                    name = "无权限接口";
                }

            } catch (Exception e) {
                name = "无权限接口";
            }
        } else {
            name = "无权限接口";
            username = "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SystemLog systemLog = new SystemLog();
        //获取客户端请求信息
        systemLog.setPermission(name);
        systemLog.setIp(ip);
        //代理
        systemLog.setUseragent(request.getHeader("User-Agent"));
        //用户登录名
        systemLog.setUsername(username);
        //访问路径
        String URI = request.getRequestURI();
        systemLog.setUri(URI);
        //时间
        systemLog.setTime(sdf.format(new Date()));
        logDao = new LogDao(systemLog);
        Thread t = new Thread(logDao);
        t.start();
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
