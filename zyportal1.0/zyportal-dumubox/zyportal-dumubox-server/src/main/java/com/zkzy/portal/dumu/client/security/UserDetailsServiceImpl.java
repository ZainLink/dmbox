//package com.zkzy.portal.dumu.client.security;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.zkzy.portal.common.web.util.IpUtils;
//import com.zkzy.portal.common.web.util.IpUtils_baidu;
//import com.zkzy.portal.dumu.client.security.model.AuthUserFactory;
//import com.zkzy.portal.dumu.server.system.provider.mapper.base.SystemUserMapper;
//import com.zkzy.zyportal.system.api.entity.base.Groups;
//import com.zkzy.zyportal.system.api.entity.base.SystemUser;
//import com.zkzy.zyportal.system.api.service.base.ISystemUserService;
//import com.zkzy.zyportal.system.api.service.base.SystemMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
///**
// * Security User Detail Service
// *
// * @author admin
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    /**
//     * 系统服务
//     */
//    @Autowired
//    private ISystemUserService systemUserService;
//    @Autowired
//    private SystemMenuService systemMenuService;
//    @Autowired
//    private HttpServletRequest request;
//
//    private static String status = "1";
//
//    public static final String GROUPS = "groups-";
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Autowired
//    private SystemUserMapper SystemUserMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) {
//        SystemUser user = systemUserService.getUserByLoginName(userName);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
//        } else {
//            //更新user的数据
//            user.setUpdateDate(new Date());
//            try {
//                String ip = IpUtils.getIpAddress(request);
//                user.setIp(ip);
//                if (ip.equals("127.0.0.1")) {
//                    user.setArea("IANA");
//                    user.setLng("");
//                    user.setLat("");
//                } else {
//                    Map<String, String> map = IpUtils_baidu.getIpInfo(ip);
//                    if (map != null) {
//                        user.setArea(map.get("city"));
//                        user.setLng(map.get("lng"));
//                        user.setLat(map.get("lat"));
//                    }
//                }
//
//                //帐号无问题，就根据username 查询他的分组 分组为空 就无权限
//                Groups groups = SystemUserMapper.getGropsByUserName(user.getUsername());
//                String json = JSONObject.toJSONString(groups);
//                redisTemplate.opsForValue().set(GROUPS + user.getUsername(), json);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                return AuthUserFactory.create_AuthSystemUsers(user);
//            }
//        }
//    }
//
//
//}
