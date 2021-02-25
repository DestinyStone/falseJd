package com.hypocrisy.maven.hypocrisycart.service.impl;

import bean.OmsCartItem;
import com.hypocrisy.maven.hypocrisycart.mapper.OmsCartItemMapper;
import com.hypocrisy.maven.hypocrisycart.service.OmsCartItemService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.Message;
import response.type.ResponseCodeType;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 20:23
 * @Description:
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {

    @Autowired
    private OmsCartItemMapper omsCartItemMapper;

    @Override
    public Message insertBySkuIdAndQuantity(List<OmsCartItem> cartItemListQuery) {
        cartItemListQuery.forEach(omsCartItem -> {
            OmsCartItem omsCartItemQuery = OmsCartItem.builder().productSkuId(omsCartItem.getProductSkuId()).memberId(omsCartItem.getMemberId()).build();
            OmsCartItem omsCartItemResult = omsCartItemMapper.selectOne(omsCartItemQuery);
            if (omsCartItemResult == null) {
                omsCartItemMapper.insertSelective(omsCartItem);
            } else {
                omsCartItemResult.setQuantity(omsCartItem.getQuantity() + omsCartItemResult.getQuantity());
                omsCartItemMapper.updateByPrimaryKeySelective(omsCartItemResult);
            }
        });
        return new Message(ResponseCodeType.SUCCESS, null, true);
    }

    @Override
    public Message selectByUserId(String userId) {
        OmsCartItem omsCartItemQuery = OmsCartItem.builder().memberId(userId).build();
        List<OmsCartItem> omsCartItemList = omsCartItemMapper.select(omsCartItemQuery);
        return new Message(ResponseCodeType.SUCCESS, omsCartItemList, true);
    }

    @Override
    public Message updateCartTotal(String userId, String id, Integer skuTotal) {
        return null;
    }

    @Override
    public void decrByRepository(String productSkuId, Integer quantity) {
        omsCartItemMapper.decrByRepository(productSkuId, quantity);
    }

    @Override
    public List<OmsCartItem> selectBySkuIdsAndUserId(String userId, String[] skuIds) {
        System.out.println(RootContext.getXID());
        return omsCartItemMapper.selectBySkuIdsAndUserId(userId, skuIds);
    }

    @Override
    public void deleteByPrimaryKey(OmsCartItem omsCartItem) {
        omsCartItemMapper.deleteByPrimaryKey(omsCartItem);
    }
}
