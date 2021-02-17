package service;

import bean.PmsProductInfo;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/13 22:42
 * @Description:
 */
public interface PmsProductInfoService {

    /**
     * 根据productId更新商品状态
     * @param id
     * @param status
     * @return
     */
    Message updateStautsById(String id, Integer status);

    /**
     * 设置product拒绝内容
     * @param id
     * @param content
     * @return
     */
    Message rejectPmsProductInfo(String id, String content);

    /**
     * 查询多个product
     * @param toString
     * @param toString1
     * @param page
     * @param size
     * @param name
     * @param value
     * @return
     */
    Message selectByMultiConditions(String toString, String toString1, int page, int size, String name, String value);

    /**
     * 获取product详情
     * @param id
     * @return
     */
    Message selectDetailById(String id);

    /**
     * 插入商品
     * @param id
     * @param pmsProductInfo
     * @return
     */
    Message insert(String id, PmsProductInfo pmsProductInfo);

    /**
     *
     * @param id
     * @return
     */
    Message getUserProductInfoByUserId(String id);

    /**
     * 更新product
     * @param pmsProductInfo
     * @return
     */
    Message updateById(PmsProductInfo pmsProductInfo);
}
