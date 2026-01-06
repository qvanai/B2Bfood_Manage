package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * B2B食品管理系统启动类
 */
@SpringBootApplication
@MapperScan("com.group8.mapper")
public class B2BFoodManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2BFoodManageApplication.class, args);
    }

}