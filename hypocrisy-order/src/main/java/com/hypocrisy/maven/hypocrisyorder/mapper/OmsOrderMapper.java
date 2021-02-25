package com.hypocrisy.maven.hypocrisyorder.mapper;

import bean.OmsOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 21:53
 * @Description:
 */
@Repository
public interface OmsOrderMapper extends Mapper<OmsOrder> {

    void updateOrderStatus(@Param("orderNo") String orderNo, @Param("status") Integer status);

    List<OmsOrder> selectContainOrderItem(@Param("orderIds") String[] orderIds);

    List<OmsOrder> selectByUserIdAndStatusAndSkuName(@Param("userId") String userId, @Param("filter") String filter, @Param("search") String search);
}
