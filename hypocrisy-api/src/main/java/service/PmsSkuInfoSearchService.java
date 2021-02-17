package service;

import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/10 22:02
 * @Description:
 */
public interface PmsSkuInfoSearchService {
    /**
     * 查询商品sku
     * @param keyword
     * @param valueIds
     * @param catalog3Id
     * @param currentPage
     * @param size
     * @param priceGte
     * @param priceLte
     * @param sort
     * @return
     */
    Message selectByCondition(String keyword, String[] valueIds, String catalog3Id, String currentPage, String size, String priceGte, String priceLte, String sort);
}
