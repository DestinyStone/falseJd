package com.hypocrisy.maven.hypocrisyorder.controller;

import com.hypocrisy.maven.hypocrisyorder.service.OmsOrderService;
import feign.service.FeignOmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 18:48
 * @Description:
 */
@RestController
public class FeignOrderController implements FeignOmsOrderService {

    @Autowired
    private OmsOrderService omsOrderService;

    @GetMapping("/getOrderDetail")
    public Message getOrderDetail(@RequestParam("orderNo") String orderNo, @RequestParam("userId") String userId){
        return omsOrderService.getOrderDetail(orderNo, userId);
    }

    @GetMapping("/updateOrderStatus")
    public Message updateOrderStatus(@RequestParam("orderNo") String orderNo, @RequestParam("userId") int status){
        return omsOrderService.updateOrderStatus(orderNo, status);
    }
}
