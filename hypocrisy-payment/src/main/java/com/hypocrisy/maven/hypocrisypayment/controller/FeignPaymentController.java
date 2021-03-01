package com.hypocrisy.maven.hypocrisypayment.controller;

import com.hypocrisy.maven.hypocrisypayment.service.impl.AlipaymentServiceImpl;
import feign.service.FeignAlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 21:06
 * @Description:
 */
@RestController
public class FeignPaymentController implements FeignAlipayService {

    @Autowired
    private AlipaymentServiceImpl alipaymentService;

    @GetMapping("/queryOrderAlipayStatus")
    @Override
    public Message queryOrderAlipayStatus(@RequestParam("orderNo") String orderNo) {
        return alipaymentService.queryOrderAlipayStatus(orderNo);
    }
}
