package com.hypocrisy.maven.hypocrisypayment.controller;

import bean.OmsOrder;
import com.alibaba.fastjson.JSONObject;
import com.hypocrisy.maven.hypocrisypayment.factory.concreten.PaymentFactory;
import com.hypocrisy.maven.hypocrisypayment.service.PaymentService;
import feign.service.FeignOmsOrderService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.Message;
import response.type.ResponseCodeType;
import security.details.UmsMemberDetails;
import type.PaymentType;

import java.util.HashMap;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 17:51
 * @Description:
 */
@RestController
public class PaymentController {

    @Autowired
    private FeignOmsOrderService feignOmsOrderService;


    @GetMapping(value = "/getAlipayQrcode", params = {"orderNo!="})
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("获取支付二维码")
    public Message getAlipayQrcode(@RequestParam("orderNo") String orderNo,
                                   @RequestParam("paymentType") PaymentType paymentType,
                                   @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {

        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        Message orderDetail = feignOmsOrderService.getOrderDetail(orderNo, userId);
        HashMap<String, Object> result = (HashMap) orderDetail.getResponseMessage();

        OmsOrder omsOrder = JSONObject.parseObject(JSONObject.toJSONString(result.get("omsOrder")), OmsOrder.class);

        // 只有订单未支付才会创建二维码
        if (omsOrder.getStatus() != 0) {
            return new Message(ResponseCodeType.SUCCESS, "订单以支付", true);
        }

        PaymentService paymentService = PaymentFactory.getPaymentService(paymentType);
        String qrcodeBase64 = paymentService.createQrcode(omsOrder.getOrderSn(), omsOrder.getTotalAmount());
        if (StringUtils.isBlank(qrcodeBase64)) {
            return new Message(ResponseCodeType.UN_KNOW_ERROR, null, false);
        }
        return new Message(ResponseCodeType.SUCCESS, qrcodeBase64, true);
    }

    @GetMapping(value="/queryOrderAlipayStatus", params = {"orderNo!="})
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("获取支付状态")
    public Message queryOrderStatus(@RequestParam("orderNo") String orderNo, PaymentType paymentType) {
        PaymentService paymentService = PaymentFactory.getPaymentService(paymentType);
        Message message = paymentService.queryOrderAlipayStatus(orderNo);
        if (ResponseCodeType.TRADE_SUCCESS.equals(message.getResponseCode())) {
            // 修改订单状态为已支付, 需要rabbit确保修改成功
            feignOmsOrderService.updateOrderStatus(orderNo, 1);
        }
        return message;
    }
}
