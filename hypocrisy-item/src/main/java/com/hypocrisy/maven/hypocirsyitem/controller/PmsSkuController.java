package com.hypocrisy.maven.hypocirsyitem.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.Message;
import response.type.ResponseCodeType;
import com.hypocrisy.maven.hypocirsyitem.service.PmsSkuInfoService;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 16:38
 * @Description:
 */
@RestController
@CrossOrigin
public class PmsSkuController {

    @Autowired
    private PmsSkuInfoService pmsSkuInfoService;

    @GetMapping(value = "/getPmsSkuInfoById", params = {"id!="})
    @ApiOperation("根据skuId查询商品")
    public Message getPmsSkuInfoById(@RequestParam("id") String id) {
        return pmsSkuInfoService.selectById(id);
    }

    @GetMapping(value="/getPmsSkuAttrListBySkuId", params = {"id!="})
    @ApiOperation("查询当前系列商品下的所有销售属性")
    public Message getPmsSkuAttrListBySkuId(@RequestParam("id") String id) {
        return pmsSkuInfoService.selectAllSkuAttrListById(id);
    }

    @PostMapping(value = "/getPmsSkuInfoByValueIds")
    @ApiOperation("根据销售属性查找商品")
    public Message getPmsSkuInfoByValueId(String spuId, String[] valueIds) {
        Integer skuId = pmsSkuInfoService.selectValueIds(spuId, valueIds);
        if (skuId == null) {
            return new Message(ResponseCodeType.NO_COMMODITY, null, true);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("skuId", skuId);

        return new Message(ResponseCodeType.SUCCESS, jsonObject, true);
    }

    @PostMapping("/getPmsSkuInfoBySkuIds")
    @ApiOperation("查找多个sku商品")
    public Message getPmsSkuInfoBySkuIds(@RequestBody String[] skuIds) {
        if (skuIds.length == 0)
            return new Message(ResponseCodeType.SUCCESS, null, true);
        return pmsSkuInfoService.selectByIds(skuIds);
    }
}
