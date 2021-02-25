package com.hypocrisy.maven.hypocrisypassport;

import annon.EnableSecurity;
import annon.ImportCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hypocrisy.maven.hypocrisypassport.mapper")
@ImportCommon
@EnableSecurity
public class HypocrisyPassportApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypocrisyPassportApplication.class, args);
    }

}
