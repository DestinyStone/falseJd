package com.hypocrisy.maven.hypocrisypassport.common;

import org.apache.http.HttpResponse;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/3 21:49
 * @Description:
 */
public class SendVerifyCodeClient {

    private String host = "https://intlsms.market.alicloudapi.com";
    private String path = "/comms/sms/sendmsgall";
    private String method = "POST";
    private String appcode;

    public SendVerifyCodeClient(String appcode) {
        this.appcode = appcode;
    }

    public boolean sendPhoneVerifyCode(String phone, String verifyCode) {
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");
        bodys.put("channel", "0");
        bodys.put("mobile", "+86" + phone);
        bodys.put("templateID", "20201017102046");
        bodys.put("templateParamSet", "["+ verifyCode +", '1']");

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
