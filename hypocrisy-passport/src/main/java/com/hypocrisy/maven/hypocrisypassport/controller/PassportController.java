package com.hypocrisy.maven.hypocrisypassport.controller;

import bean.UmsMember;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.Message;
import response.type.ResponseCodeType;
import utils.AccessControlUtils;
import utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 17:26
 * @Description:
 */
@RestController
@CrossOrigin
public class PassportController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("用户登录")
    @PostMapping(value = "/login", params = {"username!=", "password!="})
    public Message login(String username, String password, String permission, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
        subject.checkPermission(permission==null?"4000":permission);

        String token = JWTUtils.code("token", username, 1000 * 60 * 60);
        AccessControlUtils.setToken(response);
        response.setHeader("token", token);
        return new Message(ResponseCodeType.SUCCESS, "登录成功", true);
    }

    @ApiOperation("查询用户登录状态")
    @GetMapping("/loginStatus")
    public Message loginStatus() {
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            return new Message(ResponseCodeType.LOGIN_STATUS, "用户已登录", true);
        } else {
            return new Message(ResponseCodeType.NO_LOGIN_STATUS, "用户未登录", true);
        }
    }

    @GetMapping("/getUser")
    @ApiOperation("获取用户")
    public Message getUser(HttpServletRequest request) {
        Message message = this.loginStatus();
        if (message.getResponseCode().equals(ResponseCodeType.NO_LOGIN_STATUS))
            return message;

        Subject subject = SecurityUtils.getSubject();

        UmsMember umsMember = (UmsMember)subject.getPrincipal();
        UmsMember umsMemberResult = UmsMember.builder().username(umsMember.getUsername()).build();
        return new Message(ResponseCodeType.SUCCESS, umsMemberResult, true);
    }

    @GetMapping("selectByUsername")
    UmsMember selectByUsername(@RequestParam("username") String username){
        return umsMemberService.selectByUsername(username);
    }


    @GetMapping("selectMemberPermission")
    String selectMemberPermission(@RequestParam("permissionId") String permissionId){
        return umsMemberService.selectMemberPermission(permissionId);
    }
}
