package service;

import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/14 16:59
 * @Description:
 */
public interface PmsProductRejectService {
    /**
     * 查询商品拒绝内容
     * @param spuId
     * @return
     */
    Message selectBySpuId(String spuId);
}
