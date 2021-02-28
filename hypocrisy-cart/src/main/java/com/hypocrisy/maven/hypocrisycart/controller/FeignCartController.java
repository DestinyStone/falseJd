package com.hypocrisy.maven.hypocrisycart.controller;

import bean.OmsCartItem;
import com.hypocrisy.maven.hypocrisycart.service.OmsCartItemService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 22:06
 * @Description:
 */
@CrossOrigin
@RestController
public class FeignCartController {

    @Autowired
    private OmsCartItemService omsCartItemService;

    @GetMapping("/decrByRepository")
    public void decrByRepository(@RequestParam("skuId") String productSkuId, @RequestParam("repository") Integer quantity) {
        omsCartItemService.decrByRepository(productSkuId, quantity);
    }

    @GetMapping("/selectBySkuIdsAndUserId")
    public List<OmsCartItem> selectBySkuIdsAndUserId(@RequestParam("userId") String userId, @RequestParam("skuIds") String[] skuIds) {
        System.out.println(RootContext.getXID());
        return omsCartItemService.selectBySkuIdsAndUserId(userId, skuIds);
    }

    @PostMapping("/deleteByPrimaryKey")
    public void deleteByPrimaryKey(@RequestBody OmsCartItem omsCartItem) {
        System.out.println( RootContext.getXID() + "----------------------------------------");
        omsCartItemService.deleteByPrimaryKey(omsCartItem);
    }
}
