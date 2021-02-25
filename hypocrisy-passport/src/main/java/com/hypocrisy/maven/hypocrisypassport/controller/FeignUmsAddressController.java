package com.hypocrisy.maven.hypocrisypassport.controller;

import bean.UmsMemberReceiveAddress;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 23:22
 * @Description:
 */
@RestController
@CrossOrigin
public class FeignUmsAddressController{

    @Autowired
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;

    @PostMapping("/selectOne")
    UmsMemberReceiveAddress selectOne(@RequestBody UmsMemberReceiveAddress umsMemberReceiveAddressQuery) {
        return umsMemberReceiveAddressService.selectOne(umsMemberReceiveAddressQuery);
    }

}
