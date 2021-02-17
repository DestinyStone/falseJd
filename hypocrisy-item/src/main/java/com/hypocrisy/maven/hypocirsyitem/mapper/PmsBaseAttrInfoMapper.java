package com.hypocrisy.maven.hypocirsyitem.mapper;

import bean.PmsBaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 17:04
 * @Description:
 */
@Repository
public interface PmsBaseAttrInfoMapper extends Mapper<PmsBaseAttrInfo> {

    List<PmsBaseAttrInfo> selectBySkuIds(@Param("ids") String[] ids, @Param("useSpuId") String id);
}
