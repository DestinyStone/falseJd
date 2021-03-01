package feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 18:45
 * @Description:
 */
@FeignClient(name = "hypocrisy-order")
public interface FeignOmsOrderService {
    /**
     * 获取订单详情
     * @param orderNo
     * @param userId
     * @return
     */
    @GetMapping("/getOrderDetail")
    Message getOrderDetail(@RequestParam("orderNo") String orderNo, @RequestParam("userId") String userId);

    /**
     * 更新订单状态
     * @param orderNo
     * @param status
     * @return
     */
    @GetMapping("/updateOrderStatus")
    Message updateOrderStatus(@RequestParam("orderNo") String orderNo, @RequestParam("userId") int status);
}
