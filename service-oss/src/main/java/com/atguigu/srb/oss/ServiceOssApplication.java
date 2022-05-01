package com.atguigu.srb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wanfeng
 * @created 2022/4/30 20:38
 * @package com.atguigu.srb.oss
 */
@SpringBootApplication
@ComponentScan({"com.atguigu.srb", "com.atguigu.srb.common"})
public class ServiceOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }

}
