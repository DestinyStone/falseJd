package com.hypocrisy.maven.hypocrisysearch.controller;

import io.swagger.annotations.ApiOperation;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.Message;
import response.type.ResponseCodeType;
import com.hypocrisy.maven.hypocrisysearch.service.PmsSkuInfoSearchService;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 15:16
 * @Description:
 */
@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    private PmsSkuInfoSearchService pmsSkuInfoSearchService;

    @GetMapping("/searchSkuInfoList")
    @ApiOperation("搜索sku")
    public Message searchSkuInfoList(@RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam(value = "valueIds", required = false) String[] valueIds,
                                     @RequestParam(value = "catalog3Id", required = false) String catalog3Id,
                                     @RequestParam(value = "currentPage") String currentPage,
                                     @RequestParam(value = "size") String size,
                                     @RequestParam(value = "priceGte", required = false) String priceGte,
                                     @RequestParam(value = "priceLte", required = false) String priceLte,
                                     @RequestParam(value = "sort", required = false) String sort) {
        if (StringUtils.isBlank(keyword) && StringUtils.isBlank(catalog3Id) && valueIds == null) {
            return new Message(ResponseCodeType.PARAM_ERROR, null, false);
        }
        return pmsSkuInfoSearchService.selectByCondition(keyword, valueIds, catalog3Id, currentPage, size, priceGte, priceLte, sort);
    }
}
