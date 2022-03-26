package com.bingoabin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bingoabin.persistence.mapper.gen")
public class StartApp {
    public static void main(String[] args) {
        System.out.println("开始启动！");
        SpringApplication.run(StartApp.class, args);
        System.out.println("启动成功！");
    }
}