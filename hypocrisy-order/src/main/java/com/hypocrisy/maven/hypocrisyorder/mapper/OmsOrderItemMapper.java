package com.hypocrisy.maven.hypocrisyorder.mapper;

import bean.OmsOrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 21:57
 * @Description:
 */
@Repository
public interface OmsOrderItemMapper extends Mapper<OmsOrderItem>, MySqlMapper<OmsOrderItem> {
}
