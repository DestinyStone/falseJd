package com.hypocrisy.maven.hypocrisypassport;

import annon.EnableShiro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableShiro
@MapperScan("com.hypocrisy.maven.hypocrisypassport.mapper")
public class HypocrisyPassportApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypocrisyPassportApplication.class, args);
    }

}
