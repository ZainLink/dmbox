package com.zkzy.portal.common.web.config;

import ch.qos.logback.access.servlet.TeeFilter;


import com.zkzy.portal.common.web.fliter.CrosXssFilter;
import com.zkzy.portal.common.web.logFilter.LogFilter;
import com.zkzy.portal.common.web.logFilter.PagSizeFilter;
import com.zkzy.portal.common.web.mapper.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * WEB配置类
 *
 * @author admin
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/myResource/**").addResourceLocations("classpath:/myResource/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * Remote ip filter remote ip filter.
     *
     * @return the remote ip filter
     */
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * Tee filter tee filter.
     *
     * @return the tee filter
     */
    @Bean
    @ConditionalOnProperty(prefix = "server.tomcat.accesslog", name = "debug", havingValue = "true")
    public TeeFilter teeFilter() {
        //复制请求响应流，用于打印调试日志
        return new TeeFilter();
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CrosXssFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("getSign", "/userSystem/wxScan/getSign");
        registration.setName("crosFilter");
        return registration;
    }

    @Bean
    public FilterRegistrationBean pageSizeFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new PagSizeFilter());
        registration.addUrlPatterns("/*");
        registration.setName("pagSizeFilter");
        return registration;
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

    /**
     * Object mapper object mapper.
     *
     * @return the object mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new JsonMapper();
    }

    /**
     * Http message converter http message converter.
     *
     * @return the http message converter
     */
    @Bean
    public HttpMessageConverter httpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(this.objectMapper());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false) // 系统对外暴露的 URL 不会识别和匹配 .* 后缀
                .setUseTrailingSlashMatch(true); // 系统不区分 URL 的最后一个字符是否是斜杠 /
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        // 等价于<mvc:default-servlet-handler />, 对静态资源文件的访问, 将无法 mapping 到 Controller 的 path 交给 default servlet handler 处理
        configurer.enable();
    }

    /**
     * Validator local validator factory bean.
     *
     * @return the local validator factory bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Gets method validation post processor.
     *
     * @return the method validation post processor
     */
    @Bean
    public MethodValidationPostProcessor getMethodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    /**
     * Container customizer embedded servlet container customizer.
     *
     * @return the embedded servlet container customizer
     */
    @Bean
    @ConditionalOnProperty(prefix = "server.tomcat.accesslog", name = "debug", havingValue = "true")
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new ContainerAccessLogCustomizer("logback-access.xml");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogFilter()).addPathPatterns("/**");
    }

}
