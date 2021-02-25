package com.hypocrisy.maven.hypocrisycart.mapper;

import bean.OmsCartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 20:25
 * @Description:
 */
@Repository
public interface OmsCartItemMapper extends Mapper<OmsCartItem> {

    List<OmsCartItem> selectBySkuIdsAndUserId(@Param("userId") String userId, @Param("skuIds") String[] skuIds);

    void decrByRepository(@Param("skuId") String productSkuId, @Param("repository") Integer quantity);
}
