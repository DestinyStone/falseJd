package feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 21:03
 * @Description:
 */
@FeignClient(name = "hypocrisy-payment")
public interface FeignAlipayService {
    @GetMapping("/queryOrderAlipayStatus")
    Message queryOrderAlipayStatus(@RequestParam("orderNo") String orderNo);
}
