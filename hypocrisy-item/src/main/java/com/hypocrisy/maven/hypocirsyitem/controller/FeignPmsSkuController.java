package com.hypocrisy.maven.hypocirsyitem.controller;

import com.hypocrisy.maven.hypocirsyitem.service.PmsSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 22:17
 * @Description:
 */
@RestController
@CrossOrigin
public class FeignPmsSkuController {

    @Autowired
    private PmsSkuInfoService pmsSkuInfoService;

    @GetMapping("/selectByIds")
    Message selectByIds(@RequestParam("skuIds") String[] skuIds) {
        return pmsSkuInfoService.selectByIds(skuIds);
    }
}
