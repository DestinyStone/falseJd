package com.hypocrisy.maven.hypocrisypayment;

import annon.EnableSecurity;
import annon.ImportCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportCommon
@EnableSecurity
public class HypocrisyPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypocrisyPaymentApplication.class, args);
    }

}
