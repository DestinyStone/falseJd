package service;

import bean.OmsOrder;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/1 12:52
 * @Description:
 */
public interface OmsOrderService {

    /**
     * 生成订单
     * @param userId
     * @param skuIds
     * @param addressId
     * @return
     */
    Message genOrder(String userId, String[] skuIds, String addressId);

    /**
     * 获取订单详情
     * @param orderNo
     * @param userId
     * @return
     */
    Message getOrderDetail(String orderNo, String userId);

    /**
     * 更新订单状态
     * @param orderNo
     * @param status
     * @return
     */
    Message updateOrderStatus(String orderNo, int status);

    /**
     * 获取用户的全部地址
     * @param userId
     * @param page
     * @param size
     * @param filter
     * @param search
     * @return
     */
    Message getAllOrderDetail(String userId, int page, int size, String filter, String search);

    /**
     * 根据用户id和订单号查询订单
     * @param userId
     * @param orderNo
     * @return
     */
    OmsOrder selectByUserIdAndOrderNo(String userId, String orderNo);

    /**
     * 删除订单
     * @param userId
     * @param orderId
     * @return
     */
    Message deleteOrder(String userId, String orderId);
}
