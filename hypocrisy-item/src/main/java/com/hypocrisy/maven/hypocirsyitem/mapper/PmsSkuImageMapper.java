package com.hypocrisy.maven.hypocirsyitem.mapper;

import bean.PmsSkuImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 16:48
 * @Description:
 */
@Repository
public interface PmsSkuImageMapper extends Mapper<PmsSkuImage> {
    List<PmsSkuImage> selectImageBySkuId(@Param("id") String id);
}
