package feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 21:20
 * @Description:
 */
@FeignClient(name = "hypocrisy-item")
public interface FeignPmsSkuInfoService {

    @GetMapping("selectByIds")
    Message selectByIds(@RequestParam("skuIds") String[] skuIds);
}
