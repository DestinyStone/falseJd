package service;

import bean.OmsCartItem;
import response.Message;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/31 09:10
 * @Description:
 */
public interface OmsCartItemService {
    /**
     * 向购物车中插入商品
     * @param cartItemListQuery
     * @return
     */
    Message insertBySkuIdAndQuantity(List<OmsCartItem> cartItemListQuery);

    /**
     * 根据用户id查询购物车
     * @param userId
     * @return
     */
    Message selectByUserId(String userId);

    /**
     * 更新购物车数量
     * @param userId
     * @param id
     * @param skuTotal
     * @return
     */
    Message updateCartTotal(String userId, String id, Integer skuTotal);
}
