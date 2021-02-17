package com.hypocrisy.maven.hypocirsyitem.server.impl;

import bean.PmsBaseAttrInfo;
import bean.PmsBaseAttrValue;
import bean.PmsSkuImage;
import bean.PmsSkuInfo;
import com.hypocrisy.maven.hypocirsyitem.mapper.PmsBaseAttrInfoMapper;
import com.hypocrisy.maven.hypocirsyitem.mapper.PmsSkuImageMapper;
import com.hypocrisy.maven.hypocirsyitem.mapper.PmsSkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.Message;
import response.type.ResponseCodeType;
import service.PmsSkuInfoService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 16:45
 * @Description:
 */
@Service
public class PmsSkuInfoServiceImpl implements PmsSkuInfoService {

    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    private PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    private PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Override
    public Message selectById(String id) {
        PmsSkuInfo pmsSkuInfoQuery = PmsSkuInfo.builder().id(id).status(1).build();
        PmsSkuInfo pmsSkuInfoResult = pmsSkuInfoMapper.selectOne(pmsSkuInfoQuery);

        if (pmsSkuInfoResult == null) {
            return new Message(ResponseCodeType.NO_COMMODITY, "商品未销售", true);
        }

        List<PmsSkuImage> pmsSkuImageList = pmsSkuImageMapper.selectImageBySkuId(id);
        pmsSkuInfoResult.setPmsSkuImageList(pmsSkuImageList);
        return new Message(ResponseCodeType.SUCCESS, pmsSkuInfoResult, true);
    }

    @Override
    public Message selectAllSkuAttrListById(String id) {
        PmsSkuInfo pmsSkuInfoIdQuery = PmsSkuInfo.builder().id(id).build();
        PmsSkuInfo pmsSkuInfoResult = pmsSkuInfoMapper.selectOne(pmsSkuInfoIdQuery);
        PmsSkuInfo pmsSkuInfoSpuIdQuery = PmsSkuInfo.builder().spuId(pmsSkuInfoResult.getSpuId()).build();
        List<PmsSkuInfo> pmsSkuInfoList = pmsSkuInfoMapper.select(pmsSkuInfoSpuIdQuery);
        String[] ids = pmsSkuInfoList.stream().map(PmsSkuInfo::getId).toArray(String[]::new);
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrInfoMapper.selectBySkuIds(ids, id);

        // 对二级属性进行排序
        for (PmsBaseAttrInfo pmsBaseAttrInfo : pmsBaseAttrInfoList) {
            List<PmsBaseAttrValue> pmsBaseAttrValueListSortResult = pmsBaseAttrInfo.getPmsBaseAttrValueList().stream().sorted(Comparator.comparing(PmsBaseAttrValue::getId)).collect(Collectors.toList());
            pmsBaseAttrInfo.setPmsBaseAttrValueList(pmsBaseAttrValueListSortResult);
        }

        return new Message(ResponseCodeType.SUCCESS, pmsBaseAttrInfoList, true);
    }

    @Override
    public Integer selectValueIds(String spuId, String[] valueIds) {
        Integer i = pmsSkuInfoMapper.selectByValueIds(spuId, valueIds);
        return i;
    }
}
