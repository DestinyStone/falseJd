package com.hypocrisy.maven.hypocrisysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HypocrisySearchApplication {
//    static {
//        System.setProperty("es.set.netty.runtime.available.processors","false");
//    }
    public static void main(String[] args) {
        SpringApplication.run(HypocrisySearchApplication.class, args);
    }

}
