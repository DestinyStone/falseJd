package com.hypocrisy.maven.hypocrisypassport.controller;

import bean.UmsMemberReceiveAddress;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import response.Message;
import security.details.UmsMemberDetails;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 12:49
 * @Description:
 */
@RestController
@CrossOrigin
public class UmsAddressController {

    @Autowired
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;

    @PostMapping("/addUmsMemberAddress")
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("添加用户地址")
    public Message addUmsMemberAddress(@RequestBody @Validated UmsMemberReceiveAddress umsMemberReceiveAddress,
                                       @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        return umsMemberReceiveAddressService.insertAndSetUserId(umsMemberReceiveAddress, userId);
    }

    @GetMapping("/getUmsMemberAddress")
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("获取用户地址")
    public Message getUmsMenberAddress(@AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        return umsMemberReceiveAddressService.selectByUserId(userId);
    }

    @GetMapping(value = "/deleteUmsMemberAddressById", params = {"id!="})
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("根据地址id删除用户地址")
    public Message deleteUmsMenberAddressById(String id, @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        return umsMemberReceiveAddressService.deleteByUserIdAndId(userId, id);
    }

    @GetMapping(value = "/setUmsMemberAddressDefaultById", params = {"id!="})
    @PreAuthorize("isAuthenticated()")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @ApiOperation("设置用户默认地址")
    public Message setUmsMemberAddressDefaultById(String id, @AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        String userId = umsMemberDetails.getUmsMemberPortion().getId();
        return umsMemberReceiveAddressService.updateDefaultStatusByUserIdAndId(userId, id);
    }
}
