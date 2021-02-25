package com.hypocrisy.maven.hypocrisypassport.mq.listener;


import com.hypocrisy.maven.hypocrisypassport.common.SendVerifyCodeClient;
import com.hypocrisy.maven.hypocrisypassport.mq.custom.VerityMQName;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.RedisKeyRule;
import utils.RedisUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/3 21:39
 * @Description:
 */
@RabbitListener(queues = VerityMQName.PHONE_QUEUE)
@Component
public class PhoneQueueListener {

    @Autowired
    private SendVerifyCodeClient sendVerifyCodeClient;

    @RabbitHandler
    public void sendPhoneVerifyHandler(String phone) throws IllegalAccessException {
        String code = generatedVerifyCode();
        RedisUtils.set(RedisKeyRule.verityKeyRule(null, phone), code, (long)RedisKeyRule.expire, TimeUnit.SECONDS);
        sendVerifyCodeClient.sendPhoneVerifyCode(phone, code);
    }

    /**
     * 产生一个4位数的随机数字
     * @return 4位数的随机数字
     */
    public String generatedVerifyCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000 + "";
    }
}
