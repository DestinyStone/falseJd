package service;

import bean.PmsBaseCatalog3;
import response.Message;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/11 16:07
 * @Description:
 */
public interface PmsBaseCatalog3Service {
    /**
     * 根据二级分类id查询三级分类
     * @param catalog2Id
     * @return
     */
    List<PmsBaseCatalog3> selectByCatalog2Id(String catalog2Id);

    /**
     * 根据三级分类id修改名称
     * @param catalog3Id
     * @param name
     */
    void updateById(String catalog3Id, String name);

    /**
     * 删除三级分类
     * @param catalog3Id
     */
    void deleteById(String catalog3Id);

    /**
     * 如果三级分类名称不重复则插入三级分类名称
     * @param name
     * @param catalog2Id
     * @return
     */
    Message insertByNotNameRepetition(String name, String catalog2Id);
}
