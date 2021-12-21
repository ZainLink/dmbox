package com.zkzy.config;

import com.zkzy.LogIn.LogIn;
import com.zkzy.init.SdkInit;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by Thinkpad-W530 on 2021/12/20.
 */
@Configuration
public class HkInitConfig {


    /**
     * 初始化sdk
     * @return
     */
    @Bean
    public SdkInit initSdk() {
        return new SdkInit();
    }

    /**
     * 多端登录
     * @return
     */
    @Bean
    public LogIn hkLogIn() {
        return new LogIn("192.168.6.188", (short) 8000, "admin", "zhiyuan@123");
    }
}
