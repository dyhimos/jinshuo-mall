package com.jinshuo.mall.front.user.user;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.core.utils.IpUtil;
import com.jinshuo.mall.service.user.application.cmd.*;
import com.jinshuo.mall.service.user.service.command.MemberCmdService;
import com.jinshuo.mall.service.user.service.command.SupplierManagerComService;
import com.jinshuo.mall.service.user.service.command.UserAccountCmdService;
import com.jinshuo.mall.service.user.service.query.MemberQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @Classname WxGoodsOrderRestApi
 * @Description TODO
 * @Date 2019/6/16 19:52
 * @Created by mgh
 */
@Slf4j
@Component
@RestController
@Validated
@RequestMapping("/v1/wx/user")
@Api(description = "微信用户接口")
@AllArgsConstructor
public class WxUserRestApi {

    @Autowired
    private UserAccountCmdService userAccountCmdService;

    @Autowired
    private MemberCmdService memberCmdService;

    @Autowired
    private MemberQueryService memberQueryService;

    @Autowired
    private SupplierManagerComService supplierManagerComService;


    @ApiOperation(httpMethod = "POST", value = "注册用户")
    @PostMapping(value = "/registered", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse registered(@Validated @RequestBody RegisteredCmd cmd, HttpServletRequest request) throws IOException {
        String ipAddr = IpUtil.getIpAddr(request);
        log.info(" -- 统计访问量ip为：" + ipAddr);
        return WrapperResponse.success(userAccountCmdService.registered(cmd));
    }

    @ApiOperation(httpMethod = "POST", value = "app登录")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse appLogin(@Validated @RequestBody AppLoginCmd appLoginCmd) throws IOException {
        return WrapperResponse.success(userAccountCmdService.appLogin(appLoginCmd));
    }

    @PostMapping("/info")
    @ApiOperation("微信用户信息")
    public WrapperResponse miniLogin() throws UnsupportedEncodingException {
        return WrapperResponse.success(userAccountCmdService.getUserInfo());
    }

    @PostMapping("/comUserInfo")
    @ApiOperation("完善客户信息")
    public WrapperResponse comUserInfo(@RequestBody CompletionMemberCmd cmd){
        return WrapperResponse.success(memberCmdService.cmpletionUserInfo(cmd));
    }

    @GetMapping("/memberInfo")
    @ApiOperation("微信用户信息")
    public WrapperResponse memberInfo() throws UnsupportedEncodingException {
        return WrapperResponse.success(memberQueryService.findByUserId());
    }


    @GetMapping("/geetest")
    @ApiOperation("图片插件")
    public WrapperResponse geetest(){
        return WrapperResponse.success(JSONObject.parse(memberCmdService.geetest()));
    }


    @PostMapping("/sendMsg")
    @ApiOperation("发送验证短信")
    public WrapperResponse sendMsg(@RequestBody ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
        memberCmdService.sendMsg(changeMobileCmd);
        return WrapperResponse.success();
    }


    @PostMapping("/upPhone")
    @ApiOperation("更新手机")
    public WrapperResponse sendMsg(@RequestBody UpMemberPhoneCmd upMemberPhoneCmd) throws UnsupportedEncodingException {
        memberCmdService.changePhone(upMemberPhoneCmd);
        return WrapperResponse.success();
    }


    @GetMapping("/getManagerInfo")
    @ApiOperation("获取核销员信息")
    public WrapperResponse getSupllierManagerInfo() throws Exception {
        return WrapperResponse.success(supplierManagerComService.getInfoByToken());
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改登录密码")
    public WrapperResponse updateLoginPassword(@RequestBody LoginPasswordCmd cmd) throws Exception {
        userAccountCmdService.updatePassword(cmd);
        return WrapperResponse.success();
    }


    @PostMapping("/sendMsgOutLogin")
    @ApiOperation("发送验证短信(未登陆)")
    public WrapperResponse sendMsgOutLogin(@RequestBody ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
        memberCmdService.sendMsgOutLogin(changeMobileCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/sendMsgSafetyPhone")
    @ApiOperation("给安全手机发送短信")
    public WrapperResponse sendMsgSafetyPhone(@RequestBody ChangeMobileCmd changeMobileCmd) throws UnsupportedEncodingException {
        memberCmdService.sendMsgSafetyPhone(changeMobileCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/resetPassword")
    @ApiOperation("重置登录密码")
    public WrapperResponse resetPassword(@RequestBody LoginPasswordCmd cmd) throws Exception {
        userAccountCmdService.resetPassword(cmd);
        return WrapperResponse.success();
    }


    @PostMapping("/setPayPassword")
    @ApiOperation("设置交易密码")
    public WrapperResponse setPayPassword(@RequestBody PayPasswordCmd cmd) throws Exception {
        userAccountCmdService.setPayPassword(cmd);
        return WrapperResponse.success();
    }


    @PostMapping("/resetPayPassword")
    @ApiOperation("重置支付密码")
    public WrapperResponse resetPayPassword(@RequestBody PayPasswordCmd cmd) throws Exception {
        userAccountCmdService.resetPayPassword(cmd);
        return WrapperResponse.success();
    }
}
