package com.demon.echarts_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demon.echarts_demo.mapper")
public class EchartsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchartsDemoApplication.class, args);
    }

}
