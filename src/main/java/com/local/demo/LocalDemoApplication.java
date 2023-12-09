package com.local.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 本地演示应用程序
 *
 * @author echo
 * @date 2023/12/09
 */
@SpringBootApplication
@MapperScan("com.local.demo.mapper")
public class LocalDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalDemoApplication.class,args);
    }
}
