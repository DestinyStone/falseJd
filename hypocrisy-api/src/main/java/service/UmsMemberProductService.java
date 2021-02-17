package service;

import bean.UmsMemberProduct;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/20 12:31
 * @Description:
 */
public interface UmsMemberProductService {
    /**
     * 获取用户所有上架商品
     * @param id
     * @return
     */
    List<UmsMemberProduct> getUemberProductAllByUemId(String id);
}
