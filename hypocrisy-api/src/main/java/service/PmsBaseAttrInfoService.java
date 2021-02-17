package service;

import bean.PmsBaseAttrInfo;
import response.Message;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/12 10:15
 * @Description:
 */
public interface PmsBaseAttrInfoService {
    /**
     * 根据三级分类id查询销售属性
     * @param catalog3Id
     * @return
     */
    List<PmsBaseAttrInfo> getPmsBaseAttrInfoByCatalog3Id(String catalog3Id);

    /**
     * 根据三级分类id添加销售属性
     * @param name
     * @param catalog3Id
     * @return
     */
    Message addPmsBaseAttrInfoByCatalog3Id(String name, String catalog3Id);

    /**
     * 更新销售属性
     * @param id
     * @param name
     * @return
     */
    Message updatePmsBaseAttrInfo(String id, String name);

    /**
     * 删除销售属性
     * @param id
     * @return
     */
    Message deletePmsBaseAttrInfo(String id);
}
