package service;

import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/21 17:01
 * @Description:
 */
public interface PmsSkuRepositoryService {
    /**
     * 更新商品库存数量
     * @param skuId
     * @param repositoryTotal
     * @return
     */
    Message updateRepositoryTotalBySkuId(String skuId, String repositoryTotal);
}
