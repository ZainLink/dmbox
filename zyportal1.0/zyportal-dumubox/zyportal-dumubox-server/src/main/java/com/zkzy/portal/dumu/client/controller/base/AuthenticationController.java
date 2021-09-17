//package com.zkzy.portal.dumu.client.controller.base;
//
//import com.zkzy.portal.common.web.util.WebUtils;
//import com.zkzy.portal.dumu.client.common.annotation.RequestLimit;
//import com.zkzy.portal.dumu.client.common.constant.Message;
//import com.zkzy.portal.dumu.client.common.constant.ReturnCode;
//import com.zkzy.portal.dumu.client.common.controller.BaseController;
//
//import com.zkzy.portal.dumu.client.security.model.AuthUser;
//import com.zkzy.portal.dumu.client.security.utils.TokenUtil;
//import com.zkzy.portal.common.redis.RedisRepository;
//import com.zkzy.portal.common.utils.UserAgent;
//import com.zkzy.portal.common.utils.UserAgentUtil;
//
//import com.zkzy.zyportal.system.api.entity.base.SystemRole;
//import com.zkzy.zyportal.system.api.entity.base.SystemUser;
//import com.zkzy.zyportal.system.api.service.base.ISystemUserService;
//import com.zkzy.zyportal.system.api.service.base.SystemMenuService;
//import io.swagger.annotations.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.text.ParseException;
//import java.util.*;
//
///**
// * The type Authentication controller.
// *
// * @author admin
// */
//@RestController
//@RequestMapping("/Permission/auth")
//@Api(tags = "权限管理")
//public class AuthenticationController extends BaseController {
//
//    @Autowired
//    private ISystemUserService systemUserService;
//    /**
//     * 权限管理
//     */
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    /**
//     * 用户信息服务
//     */
//    @Autowired
//    private UserDetailsService userDetailsService;
//    /**
//     * Token工具类
//     */
//    @Autowired
//    private TokenUtil jwtTokenUtil;
//
//    @Autowired
//    private SystemMenuService systemMenuService;
//    @Autowired
//    private RedisRepository redisRepository;
//
//    /**
//     * Create authentication token map.
//     *
//     * @param username the username
//     * @param password the password
//     * @return the map
//     */
//    @PostMapping(value = "/token", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "获取token")
//    @RequestLimit(count = 5)
//    public Map<String, Object> createAuthenticationToken(
//            @ApiParam(required = true, value = "用户名") @RequestParam(value = "username", defaultValue = "root") String username,
//            @ApiParam(required = true, value = "密码") @RequestParam(value = "password", defaultValue = "root") String password,
//            HttpServletRequest request, HttpServletResponse response
//    ) throws ParseException {
//
//        try {
//            //完成授权
//            //1、用户名、密码组合生成一个Authentication对象（也就是UsernamePasswordAuthenticationToken对象）。
//            //2、生成的这个token对象会传递给一个AuthenticationManager对象用于验证。
//            //3、当成功认证后，AuthenticationManager返回一个Authentication对象，认证后的身份。
//            //4、接下来，就可以调用
//            //SecurityContextHodler.getContext().setAuthentication(…)。
//            Authentication authentication = null;
//            try {
//                authentication = authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(username, password)
//                );
//            } catch (Exception e) {
//
//                Map<String, Object> message = new HashMap<>();
//                message.put(Message.RETURN_FIELD_CODE, ReturnCode.BAD_REQUEST);
//                message.put(Message.RETURN_FIELD_CODE_DESC, "账号或密码错误!");
//                return message;
//            }
//
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//            //生成自定义token
//            final String token = jwtTokenUtil.generateToken(userDetails);
//            String agent = request.getHeader("User-Agent");
//            UserAgent UserAgent = UserAgentUtil.getUserAgent(agent);
//
//            Map<String, Object> tokenMap = new HashMap<>();
//            tokenMap.put("access_token", token);
//            tokenMap.put("expires_in", jwtTokenUtil.getExpiration());
//            tokenMap.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);
//
//            Map<String, Object> message = new HashMap<>();
//            message.put(Message.RETURN_FIELD_CODE, ReturnCode.SUCCESS);
//            message.put(Message.RETURN_FIELD_DATA, tokenMap);
//            return message;
//        } catch (Exception e) {
//            return makeMessage(com.zkzy.zyportal.system.api.constant.ReturnCode.FAILED);
//        }
//    }
//
//
//    @GetMapping(value = "/getBasic", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "根据Token，获取用户信息")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> getUser() {
//        try {
//            AuthUser user = WebUtils.getCurrentUser();
//            SystemUser systemUser = systemUserService.getUserInfoByLoginName(user.getUsername());
//
//            if (systemUser == null) {
//                return makeMessage(com.zkzy.zyportal.system.api.constant.ReturnCode.USER_NOT_EXIST);
//            } else {
//                Map<String, Object> message = makeMessage(com.zkzy.zyportal.system.api.constant.ReturnCode.SUCCESS);
//                message.put(Message.RETURN_FIELD_DATA, systemUser);
//                return message;
//            }
//        } catch (Exception e) {
//            return makeErrorMessage(com.zkzy.zyportal.system.api.constant.ReturnCode.FAILED, null);
//        }
//    }
//
//
//}
