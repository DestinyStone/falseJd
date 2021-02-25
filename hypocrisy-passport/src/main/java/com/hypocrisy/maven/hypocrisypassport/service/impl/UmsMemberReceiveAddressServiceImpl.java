package com.hypocrisy.maven.hypocrisypassport.service.impl;

import bean.UmsMemberReceiveAddress;
import com.hypocrisy.maven.hypocrisypassport.mapper.UmsMemberReceiveAddressMapper;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.Message;
import response.type.ResponseCodeType;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 12:52
 * @Description:
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {

    @Autowired
    private UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public Message insertAndSetUserId(UmsMemberReceiveAddress umsMemberReceiveAddress, String userId) {

        UmsMemberReceiveAddress memberReceiveAddressQuery = UmsMemberReceiveAddress.builder()
                .memberId(userId)
                .name(umsMemberReceiveAddress.getName())
                .province(umsMemberReceiveAddress.getProvince())
                .city(umsMemberReceiveAddress.getCity())
                .region(umsMemberReceiveAddress.getRegion())
                .phoneNumber(umsMemberReceiveAddress.getPhoneNumber())
                .detailAddress(umsMemberReceiveAddress.getDetailAddress())
                .defaultStatus(0).build();

        umsMemberReceiveAddressMapper.insertSelective(memberReceiveAddressQuery);
        return new Message(ResponseCodeType.SUCCESS, null, true);
    }

    @Override
    public Message selectByUserId(String userId) {
        UmsMemberReceiveAddress umsMemberReceiveAddress = UmsMemberReceiveAddress.builder().memberId(userId).build();
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);


        Optional<UmsMemberReceiveAddress> first = umsMemberReceiveAddressList.stream().filter(x -> "1".equals(x.getDefaultStatus() + "")).findFirst();
        if (first.orElse(null) == null) {
            return new Message(ResponseCodeType.SUCCESS, umsMemberReceiveAddressList, true);
        }
        Collections.swap(umsMemberReceiveAddressList, 0, umsMemberReceiveAddressList.indexOf(first.get()));
        umsMemberReceiveAddressList.stream().forEach(x -> {
            String fuzzyPhone = x.getPhoneNumber().substring(0, 3) + "****" + x.getPhoneNumber().substring(7, x.getPhoneNumber().length());
            x.setPhoneNumber(fuzzyPhone);
        });
        return new Message(ResponseCodeType.SUCCESS, umsMemberReceiveAddressList, true);
    }

    @Override
    public Message deleteByUserIdAndId(String userId, String id) {
        Example example = new Example(UmsMemberReceiveAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id).andEqualTo("memberId", userId);
        umsMemberReceiveAddressMapper.deleteByExample(example);
        return new Message(ResponseCodeType.SUCCESS, null, true);
    }

    @Override
    @Transactional
    public Message updateDefaultStatusByUserIdAndId(String userId, String id) {

        Example example = new Example(UmsMemberReceiveAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", userId);
        UmsMemberReceiveAddress umsMemberReceiveAddressQuery = UmsMemberReceiveAddress.builder().defaultStatus(0).build();
        umsMemberReceiveAddressMapper.updateByExampleSelective(umsMemberReceiveAddressQuery, example);

        criteria.andEqualTo("id", id);
        umsMemberReceiveAddressQuery.setDefaultStatus(1);
        umsMemberReceiveAddressMapper.updateByExampleSelective(umsMemberReceiveAddressQuery, example);
        return new Message(ResponseCodeType.SUCCESS, null, true);
    }

    @Override
    public UmsMemberReceiveAddress selectOne(UmsMemberReceiveAddress umsMemberReceiveAddressQuery) {
        return umsMemberReceiveAddressMapper.selectOne(umsMemberReceiveAddressQuery);
    }
}
