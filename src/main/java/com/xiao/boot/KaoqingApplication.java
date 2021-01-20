package com.xiao.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.xiao.boot.mapper")
@SpringBootApplication
public class KaoqingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaoqingApplication.class, args);
    }

}
