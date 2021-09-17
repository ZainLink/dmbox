package com.zkzy.portal.dumu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement //启用事务
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
public class DumuboxServerApplication {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DumuboxServerApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DumuboxServerApplication.class, args);
        LOGGER.info("度目盒子服务端启动完成！！！");
    }

}
