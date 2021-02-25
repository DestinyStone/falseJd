package com.hypocrisy.maven.hypocrisypassport.config;

import com.hypocrisy.maven.hypocrisypassport.common.SendVerifyCodeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/3 21:46
 * @Description:
 */
@Configuration
public class SendPhoneVerifyCodeConfig {

    @Value("${phone.appcode}")
    private String appcode;

    @Bean
    public SendVerifyCodeClient sendVerifyCodeClient() {
        return new SendVerifyCodeClient(appcode);
    }
}
