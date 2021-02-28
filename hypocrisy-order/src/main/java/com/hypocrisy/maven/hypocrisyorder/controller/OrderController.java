package com.hypocrisy.maven.hypocrisyorder.controller;

import bean.OmsOrder;
import com.hypocrisy.maven.hypocrisyorder.service.OmsOrderService;
import feign.service.FeignCartItemService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.Message;
import response.type.ResponseCodeType;
import security.details.UmsMemberDetails;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 13:05
 * @Description:
 */
@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OmsOrderService omsOrderService;

    @Autowired
    private FeignCartItemService feignCartItemService;

    @GetMapping("test")
    @GlobalTransactional
    public String test() {
        System.out.println(333);
        return "333";
    }

    @GetMapping("/submitOrder")
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("提交订单")
    public Message submitOrder(@RequestParam("skuIds") String[] skuIds, @RequestParam("addressId") String addressId,
                               @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {

        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        return omsOrderService.genOrder(userId, skuIds, addressId);
    }
    @GetMapping(value = "/getOrder", params = {"orderNo!="})
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("根据订单号获取订单")
    public Message getOrder(@RequestParam("orderNo") String orderNo, @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        String userId = umsMemberDetails.getUmsMemberPortion().getId();;
        return omsOrderService.getOrderDetail(orderNo, userId);
    }

    @GetMapping (value = "/getAllOrderDetail")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token", required = true)})
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("获取订单详情")
    public Message getAllOrderDetail(@RequestParam("page") int page,
                                     @RequestParam("size") int size,
                                     @RequestParam(value = "filter", required = false) String filter,
                                     @RequestParam(value = "search", required = false) String search,
                                     @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        return omsOrderService.getAllOrderDetail(userId, page, size, filter, search);
    }

    @GetMapping(value = "/cancelOrder")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "token", required = true)})
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("根据订单号取消订单")
    public Message cancelOrder(@RequestParam("orderNo") String orderNo, @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {

        String userId = umsMemberDetails.getUmsMemberPortion().getId();

        // 检测订单号是否属于该用户的
        OmsOrder omsOrder = omsOrderService.selectByUserIdAndOrderNo(userId, orderNo);
        if (omsOrder == null) {
            return new Message(ResponseCodeType.PARAM_ERROR, "订单号异常", false);
        }
        return omsOrderService.updateOrderStatus(orderNo, 4);
    }

    @GetMapping(value = "/deleteOrder")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "token", required = true)})
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("根据订单号删除订单")
    public Message deleteOrder(@RequestParam("orderNo") String orderNo, @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {

        String userId = umsMemberDetails.getUmsMemberPortion().getId();

        // 检测订单号是否属于该用户的
        OmsOrder omsOrder = omsOrderService.selectByUserIdAndOrderNo(userId, orderNo);
        if (omsOrder == null) {
            return new Message(ResponseCodeType.PARAM_ERROR, "订单号异常", false);
        }

        // 订单状态为 0 和 5 才可删除
        if (omsOrder.getStatus() != 0 && omsOrder.getStatus() != 5) {
            return new Message(ResponseCodeType.PARAM_ERROR, "订单不可删除", false);
        }

        return omsOrderService.deleteOrder(userId, omsOrder.getId());
    }

}
