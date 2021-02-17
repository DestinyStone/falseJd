package service;

import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/13 15:07
 * @Description:
 */
public interface PmsBaseAttrValueService {
    /**
     * 根据销售属性id查询所有销售属性值
     * @param attrId
     * @return
     */
    Message selectByAttrId(String attrId);

    /**
     * 根据销售属性值删除销售属性
     * @param id
     * @return
     */
    Message deleteById(String id);

    /**
     * 查询销售属性值名字是否重复
     * @param attrId
     * @param valueName
     * @return
     */
    Message insertByNotNameRepetition(String attrId, String valueName);

    /**
     * 如果销售属性值没有重复则更新
     * @param id
     * @param valueName
     * @return
     */
    Message updateByNotNameRepetition(String id, String valueName);
}
