package com.hypocrisy.maven.hypocrisycart.controller;

import bean.OmsCartItem;
import bean.PmsSkuInfo;
import com.alibaba.fastjson.JSONObject;
import com.hypocrisy.maven.hypocrisycart.service.OmsCartItemService;
import feign.service.FeignPmsSkuInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import response.Message;
import security.details.UmsMemberDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 20:11
 * @Description:
 */
@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private FeignPmsSkuInfoService feignPmsSkuInfoService;

    @Autowired
    private OmsCartItemService omsCartItemService;

    @PostMapping("/addCart")
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("添加商品到购物车")
    public Message addCart(@RequestBody List<OmsCartItem> cartItemList, @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {

        final String userId = umsMemberDetails.getUmsMemberPortion().getId();

        final List<OmsCartItem> cartItemListQuery = cartItemList.stream()
                .filter(cartItem -> !StringUtils.isBlank(cartItem.getProductSkuId()) && cartItem.getQuantity() > 0)
                .map(cartItem -> {
                    return OmsCartItem.builder().productSkuId(cartItem.getProductSkuId()).quantity(cartItem.getQuantity()).build();
                }).collect(Collectors.toList());
        Message message = feignPmsSkuInfoService.selectByIds(cartItemListQuery.stream().map(x -> x.getProductSkuId()).toArray(String[]::new));

        List<PmsSkuInfo> pmsSkuInfoList = (List<PmsSkuInfo>)message.getResponseMessage();
        for (int i = 0; i < pmsSkuInfoList.size(); i++) {
            PmsSkuInfo pmsSkuInfoResult = JSONObject.parseObject(JSONObject.toJSONString(pmsSkuInfoList.get(i)), PmsSkuInfo.class);
            pmsSkuInfoList.set(i, pmsSkuInfoResult);
        }
        cartItemListQuery.stream().forEach(cartItem -> {
            cartItem.setMemberId(userId);
            Optional<PmsSkuInfo> first = pmsSkuInfoList.stream().filter(x -> cartItem.getProductSkuId().equals(x.getId())).findFirst();
            cartItem.setPrice(first.get().getPrice());
        });

        return omsCartItemService.insertBySkuIdAndQuantity(cartItemListQuery);
    }

    @GetMapping("/getCart")
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("获取购物车")
    public Message getCart(@AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        final String userId = umsMemberDetails.getUmsMemberPortion().getId();

        return omsCartItemService.selectByUserId(userId);
    }
}
