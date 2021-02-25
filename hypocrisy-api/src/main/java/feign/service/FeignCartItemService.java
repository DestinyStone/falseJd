package feign.service;

import bean.OmsCartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 22:03
 * @Description:
 */
@FeignClient(name = "hypocrisy-cart")
public interface FeignCartItemService {

    @GetMapping("/selectBySkuIdsAndUserId")
    List<OmsCartItem> selectBySkuIdsAndUserId(@RequestParam("userId") String userId, @RequestParam("skuIds") String[] skuIds);

    @GetMapping("/decrByRepository")
    void decrByRepository(@RequestParam("skuId") String productSkuId, @RequestParam("repository") Integer quantity);

    @PostMapping("/deleteByPrimaryKey")
    void deleteByPrimaryKey(@RequestBody OmsCartItem omsCartItem);
}
