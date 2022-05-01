package com.atguigu.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wanfeng
 * @created 2022/4/30 19:53
 * @package com.atguigu.srb.sms
 */
@SpringBootApplication
@EnableFeignClients    // openfeign
@ComponentScan({"com.atguigu.srb", "com.atguigu.srb.common"})
public class ServiceSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }
}