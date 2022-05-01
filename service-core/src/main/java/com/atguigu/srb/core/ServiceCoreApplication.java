package com.atguigu.srb.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wanfeng
 * @created 2022/4/30 11:49
 * @package com.atguigu.srb.core
 */
@SpringBootApplication
@ComponentScan({"com.atguigu.srb", "com.atguigu.srb.common"})
public class ServiceCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }
}
