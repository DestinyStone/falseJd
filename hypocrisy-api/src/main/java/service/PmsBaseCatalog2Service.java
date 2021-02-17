package service;

import bean.PmsBaseCatalog2;
import response.Message;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/11 16:07
 * @Description:
 */
public interface PmsBaseCatalog2Service {
    /**
     * 根据一级分类id查询二级分类
     * @param catalog1Id
     * @return
     */
    List<PmsBaseCatalog2> selectByCatalog1Id(String catalog1Id);

    /**
     * 根据二级分类id修改修改二级分类名称
     * @param catalog2Id
     * @param name
     */
    void updateById(String catalog2Id, String name);

    /**
     * 删除二级分类
     * @param catalog2Id
     */
    void deleteById(String catalog2Id);

    /**
     * 如果二级分类名称不重复则插入二级分类名称
     * @param name
     * @param catalog1Id
     * @return
     */
    Message insertByNotNameRepetition(String name, String catalog1Id);
}
