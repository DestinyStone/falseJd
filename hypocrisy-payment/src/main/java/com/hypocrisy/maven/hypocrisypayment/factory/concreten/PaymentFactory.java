package com.hypocrisy.maven.hypocrisypayment.factory.concreten;

import com.hypocrisy.maven.hypocrisypayment.service.PaymentService;
import com.hypocrisy.maven.hypocrisypayment.service.impl.AlipaymentServiceImpl;
import com.hypocrisy.maven.hypocrisypayment.service.impl.WeChatPaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import type.PaymentType;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 17:58
 * @Description:
 */
@Component
public class PaymentFactory {

    private static AlipaymentServiceImpl alipaymentService;

    private static WeChatPaymentServiceImpl weChatPaymentService;

    public static PaymentService getPaymentService(PaymentType paymentType) {
        if (PaymentType.ALI_PAY == paymentType) {
            return alipaymentService;
        }else if (PaymentType.WECHAT_PAY == paymentType) {
            return weChatPaymentService;
        }
        return null;
    }

    @Autowired
    public void setAlipaymentService(AlipaymentServiceImpl alipaymentService) {
        PaymentFactory.alipaymentService = alipaymentService;
    }

    @Autowired
    public void setWeChatPaymentService(WeChatPaymentServiceImpl weChatPaymentService) {
        PaymentFactory.weChatPaymentService = weChatPaymentService;
    }
}
