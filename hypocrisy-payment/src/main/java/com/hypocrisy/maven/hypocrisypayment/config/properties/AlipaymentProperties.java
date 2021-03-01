package com.hypocrisy.maven.hypocrisypayment.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 18:03
 * @Description:
 */
@Configuration
@ConfigurationProperties(
        prefix = "alipay"
)
@EnableConfigurationProperties
public class AlipaymentProperties {

    private String appId;
    private String gateWayUrl;
    private String privateKey;
    private String publicKey;
    private String signType;
    private String charset = "utf-8";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGateWayUrl() {
        return gateWayUrl;
    }

    public void setGateWayUrl(String gateWayUrl) {
        this.gateWayUrl = gateWayUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
