//package com.zkzy.portal.common.web.config;
//
//import com.zkzy.portal.common.web.security.AuthenticationTokenFilter;
//import com.zkzy.portal.common.web.security.MyAuthenticationEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * spring-security配置
// *
// *    @EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
// *    @EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，例如最常用的@PreAuthorize
// *    configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
// *    configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
// *    configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
// */
//@EnableWebSecurity
////允许进入页面方法前检验
////启用注解 可以通过注解配置权限
//
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
//public class AbstractWebSecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * 自定义用户信息服务
//     */
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    /**
//     * Password encoder password encoder.
//     *
//     * @return the password encoder
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 设置不拦截规则
//        web.ignoring().antMatchers("/sys/user/list");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //加载官方推荐的BCrypt校验
//        //auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
//        auth.userDetailsService(this.userDetailsService);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    /**
//     * Authentication token filter bean authentication token filter.
//     *
//     * @return the authentication token filter
//     */
//    @Bean
//    public AuthenticationTokenFilter authenticationTokenFilterBean() {
//        return new AuthenticationTokenFilter();
//    }
//
//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security
//            .csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint()).and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .authorizeRequests()
//            .anyRequest().authenticated();
//
//        // Custom JWT based security filter
//        security.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//}
