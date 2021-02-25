package com.hypocrisy.maven.hypocrisyorder;

import annon.EnableSecurity;
import annon.ImportCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@ImportCommon
@MapperScan("com.hypocrisy.maven.hypocrisyorder.mapper")
@SpringBootApplication
@EnableSecurity
public class HypocrisyOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypocrisyOrderApplication.class, args);
    }

}
