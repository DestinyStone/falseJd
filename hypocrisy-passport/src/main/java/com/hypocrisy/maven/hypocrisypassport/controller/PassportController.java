package com.hypocrisy.maven.hypocrisypassport.controller;

import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Message;
import response.type.ResponseCodeType;
import security.details.UmsMemberDetails;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation("用户登录")
    @PostMapping(value = "/login", params = {"username!=", "password!="})
    public Message login(String username, String password, String permission, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);


//        Subject subject = SecurityUtils.getSubject();
//        subject.login(new UsernamePasswordToken(username, password));
//        subject.checkPermission(permission==null?"4000":permission);
//
//        String token = JWTUtils.code("token", username, 1000 * 60 * 60);
//        AccessControlUtils.setToken(response);
//        response.setHeader("token", token);
        return new Message(ResponseCodeType.SUCCESS, "登录成功", true);
    }

    @ApiOperation("查询用户登录状态")
    @GetMapping("/loginStatus")
    public Message loginStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return new Message(ResponseCodeType.LOGIN_STATUS, "用户已登录", true);
        } else {
            return new Message(ResponseCodeType.NO_LOGIN_STATUS, "用户未登录", true);
        }
    }

    @GetMapping("/getUser")
    @ApiOperation("获取用户")
    @PreAuthorize("isAuthenticated()")
    public Message getUser(@AuthenticationPrincipal UmsMemberDetails umsMemberDetails) {
        return new Message(ResponseCodeType.SUCCESS, umsMemberDetails.getUmsMemberPortion(), true);
    }

    @GetMapping("/loginOut")
    @ApiOperation("退出用户")
    @PreAuthorize("isAuthenticated()")
    public Message loginOut() {
        SecurityContextHolder.clearContext();
        return new Message(ResponseCodeType.SUCCESS, "退出成功", true);
    }
}
