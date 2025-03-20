package com.example.busbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.busbackend.mapper")
public class BusssssssApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusssssssApplication.class, args);
    }
}