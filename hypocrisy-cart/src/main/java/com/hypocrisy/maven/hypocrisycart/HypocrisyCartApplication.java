package com.hypocrisy.maven.hypocrisycart;

import annon.EnableSecurity;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "feign.service")
@EnableSecurity
@MapperScan("com.hypocrisy.maven.hypocrisycart.mapper")
@EnableAutoDataSourceProxy
public class HypocrisyCartApplication {

    public static void main(String[] args) {

        SpringApplication.run(HypocrisyCartApplication.class, args);
    }

}
