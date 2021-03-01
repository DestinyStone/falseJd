package com.hypocrisy.maven.hypocrisypayment.service.impl;

import com.hypocrisy.maven.hypocrisypayment.service.PaymentService;
import org.springframework.stereotype.Component;
import response.Message;

import java.math.BigDecimal;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 18:00
 * @Description:
 */
@Component
public class WeChatPaymentServiceImpl implements PaymentService {
    @Override
    public String createQrcode(String orderSn, BigDecimal totalAmount) {
        return null;
    }

    @Override
    public Message queryOrderAlipayStatus(String orderNo) {
        return null;
    }
}
