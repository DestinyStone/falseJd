package com.hypocrisy.maven.hypocrisypassport.controller;

import bean.UmsMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UmsMemberService;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 18:21
 * @Description:
 */
@RestController
@CrossOrigin
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("selectByUsername")
    UmsMember selectByUsername(@RequestParam("username") String username){
        return umsMemberService.selectByUsername(username);
    }


    @GetMapping("selectMemberPermission")
    String selectMemberPermission(@RequestParam("permissionId") String permissionId){
        return umsMemberService.selectMemberPermission(permissionId);
    }
}
