package com.hypocrisy.maven.hypocrisypassport.controller;

import bean.UmsMember;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/19 23:23
 * @Description:
 */
@RestController
@CrossOrigin
public class FeignPassportController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("/selectByUsername")
    public UmsMember selectByUsername(@RequestParam("username") String username){
        return umsMemberService.selectByUsername(username);
    }


    @GetMapping("/selectMemberPermission")
    public String selectMemberPermission(@RequestParam("permissionId") String permissionId){
        return umsMemberService.selectMemberPermission(permissionId);
    }
}
