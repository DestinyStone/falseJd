package com.hypocrisy.maven.hypocrisypayment.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.hypocrisy.maven.hypocrisypayment.config.properties.AlipaymentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 18:02
 * @Description:
 */
@Configuration
public class AliPaymentConfig {

    @Autowired
    private AlipaymentProperties alipaymentProperties;

    @Bean
    public AlipayClient alipayClient() {
        AlipayClient alipayClient = new DefaultAlipayClient(alipaymentProperties.getGateWayUrl(),
                alipaymentProperties.getAppId(),
                alipaymentProperties.getPrivateKey(), "json",
                alipaymentProperties.getCharset(),
                alipaymentProperties.getPublicKey(),
                alipaymentProperties.getSignType());
        return alipayClient;
    }
}
