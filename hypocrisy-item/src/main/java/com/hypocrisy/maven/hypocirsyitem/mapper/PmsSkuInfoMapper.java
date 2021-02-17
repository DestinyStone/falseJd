package com.hypocrisy.maven.hypocirsyitem.mapper;

import bean.PmsSkuInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 16:47
 * @Description:
 */
@Repository
public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo> {

    Integer selectByValueIds(@Param("spuId") String spuId, @Param("valueIds") String[] valueIds);
}
