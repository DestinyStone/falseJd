package service;

import bean.PmsSkuInfo;
import response.Message;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/21 12:19
 * @Description:
 */
public interface PmsSkuInfoService {
    /**
     * 插入商品sku
     * @param pmsSkuInfo
     * @return
     */
    Message insert(PmsSkuInfo pmsSkuInfo);

    /**
     * 根据productId查询所有商品
     * @param pmsProductIds
     * @return
     */
    Message selectAllByProductIds(String[] pmsProductIds);

    /**
     * 更新商品状态
     * @param skuId
     * @return
     */
    Message updateStatusById(String skuId);

    /**
     * 查询商品
     * @param id
     * @return
     */
    Message selectById(String id);

    /**
     * 查询商品的所有销售属性
     * @param id
     * @return
     */
    Message selectAllSkuAttrListById(String id);

    /**
     * 根据销售属性值和productId查询商品
     * @param spuId
     * @param valueIds
     * @return
     */
    Integer selectValueIds(String spuId, String[] valueIds);

    /**
     * 获取多个skuId的商品
     * @param skuIds
     * @return
     */
    Message selectByIds(String[] skuIds);

    /**
     * 全部同步到es会使用该接口
     */
    List<PmsSkuInfo> selectAll();
}
