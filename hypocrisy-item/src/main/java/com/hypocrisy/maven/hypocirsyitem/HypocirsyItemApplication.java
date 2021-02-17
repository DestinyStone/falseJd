package com.hypocrisy.maven.hypocirsyitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hypocrisy.maven.hypocirsyitem.mapper")
public class HypocirsyItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypocirsyItemApplication.class, args);
    }

}
