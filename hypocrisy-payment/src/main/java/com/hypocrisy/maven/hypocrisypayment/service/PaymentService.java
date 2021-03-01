package com.hypocrisy.maven.hypocrisypayment.service;

import response.Message;

import java.math.BigDecimal;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 17:56
 * @Description:
 */
public interface PaymentService {
    /**
     * 创建支付二维码
     * @param orderSn 订单号
     * @param totalAmount 支付价格
     * @return
     */
    String createQrcode(String orderSn, BigDecimal totalAmount);

    /**
     * 查到订单支付状态
     * @param orderNo 订单号
     * @return
     */
    Message queryOrderAlipayStatus(String orderNo);
}
