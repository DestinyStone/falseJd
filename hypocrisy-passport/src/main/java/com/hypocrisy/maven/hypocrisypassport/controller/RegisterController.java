package com.hypocrisy.maven.hypocrisypassport.controller;

import bean.UmsMember;
import com.hypocrisy.maven.hypocrisypassport.mq.custom.VerityMQName;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import response.Message;
import response.type.ResponseCodeType;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/18 18:46
 * @Description:
 */
@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sendPhoneVerifyCode")
    @ApiOperation("发送手机验证码")
    public Message sendPhoneVerifyCode(String phone) {
        if (StringUtils.isBlank(phone)) return new Message(ResponseCodeType.PARAM_ERROR, null, false);

        if (umsMemberService.isActiveByPhone(phone)) {
            return  new Message(ResponseCodeType.PHONE_ALREADY_ACTIVE, "手机号已被激活", true);
        }

        rabbitTemplate.convertAndSend(VerityMQName.EXCHANGE_NAME, VerityMQName.PHONE_KEY, phone);
        return new Message(ResponseCodeType.SUCCESS, "发送成功", true);
    }

    @PostMapping("/activePhone")
    @ApiOperation("激活手机号")
    public Message activePhone(String phone, String verifyCode) throws IllegalAccessException {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(verifyCode))
            return new Message(ResponseCodeType.PARAM_ERROR, null, false);

        // 判断验证码是否激活成功
        boolean isVerifySuccess = umsMemberService.isVerifySuccess(phone, verifyCode);

        // 激活成功 插入手机号码
        if (!isVerifySuccess) {
            return new Message(ResponseCodeType.PARAM_ERROR, "验证码错误", true);
        }
        return umsMemberService.insertPhone(phone);
    }

    @PostMapping("/activeEmail")
    @ApiOperation("发送邮箱")
    public Message activeEmail(@Validated UmsMember umsMember) {
        if (StringUtils.isBlank(umsMember.getEmail())) return new Message(ResponseCodeType.PARAM_ERROR, null, false);

        Message message = umsMemberService.insertEmail(umsMember.getEmail());
        if (ResponseCodeType.PARAM_ERROR.equals(message.getResponseCode())) {
            return new Message(ResponseCodeType.PARAM_ERROR, "该邮箱已被注册", true);
        }

        rabbitTemplate.convertAndSend(VerityMQName.EXCHANGE_NAME, VerityMQName.EMAIL_KEY, umsMember.getEmail());
        return new Message(ResponseCodeType.SUCCESS, "发送成功", true);
    }

    @GetMapping(value = "/verifyMapping", params = {"email!=", "verifyCode!="})
    @ApiOperation("验证邮箱")
    public String verifyMapping(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode) {
        boolean isVerity = umsMemberService.verityCode(email, verifyCode);

        if (!isVerity) {
            return "验证失败, 验证码已过期";
        }

        Message message = umsMemberService.updateStautsTo1(email);
        return "验证成功";
    }

    @PostMapping(value = "/queryActiveStatus")
    @ApiOperation("查询激活状态")
    public Message queryActiveStatus(@Validated UmsMember umsMember) {
        if (StringUtils.isBlank(umsMember.getEmail())) return new Message(ResponseCodeType.PARAM_ERROR, "参数错误", false);
        return umsMemberService.selectEmailActiveStatus(umsMember.getEmail());
    }

    @GetMapping(value = "/isRepetitionUsername", params = {"username!="})
    @ApiOperation("检查用户名是否重复")
    public Message isRepetitionUsername(@RequestParam("username") String username) {
        int i = umsMemberService.selectByUsernameReturnCount(username);
        if (i == 0) {
            return new Message(ResponseCodeType.SUCCESS, "不存在用户名", true);
        } else {
            return new Message(ResponseCodeType.SUCCESS, "已存在用户名", false);
        }
    }

    @PostMapping(value="/addUser")
    @ApiOperation("添加一个用户")
    public Message addUser(@Validated @RequestBody UmsMember umsMember) {
        String passwordEncode = passwordEncoder.encode(umsMember.getPassword());
        if (!StringUtils.isBlank(umsMember.getEmail())) {
            return umsMemberService.addUserByEmail(umsMember.getUsername(), passwordEncode, umsMember.getEmail());
        }
        if (!StringUtils.isBlank(umsMember.getPhone())) {
            return umsMemberService.addUserByPhone(umsMember.getUsername(), passwordEncode, umsMember.getPhone());
        }
        return new Message(ResponseCodeType.PARAM_ERROR, "参数错误", false);
    }
}
