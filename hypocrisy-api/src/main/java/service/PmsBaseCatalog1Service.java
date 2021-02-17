package service;

import bean.PmsBaseCatalog1;
import response.Message;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/11 16:07
 * @Description:
 */
public interface PmsBaseCatalog1Service {
    /**
     * 查询全部一级分类
     * @return
     */
    List<PmsBaseCatalog1> selectAll();

    /**
     * 更新一级分类名称
     * @param catalog1Id
     * @param name
     */
    void updateById(String catalog1Id, String name);

    /**
     * 删除一级分类
     * @param catalog3Id
     */
    void deleteById(String catalog3Id);

    /**
     * 如果一级分类名称没有重复则插入一级分类名称
     * @param name
     * @return
     */
    Message insertByNotNameRepetition(String name);
}
