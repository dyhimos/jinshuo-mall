package com.jinshuo.mall.front.user.manager;

import com.alibaba.fastjson.JSON;
import com.jinshuo.core.model.UserAuthDto;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.service.user.application.cmd.MerchantLoginCmd;
import com.jinshuo.mall.service.user.service.command.UserAccountCmdService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PC登录
 * @author minggh
 * @Title: LoginRestApi
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/9/25 16:10
 */
@RestController
@Validated
@RequestMapping("/v1/manager/pclogin")
public class LoginRestApi {
    @Autowired
    private UserAccountCmdService userAccountCmdService;


    @ApiOperation(httpMethod = "POST", value = "登录")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse login(@RequestBody MerchantLoginCmd merchantLoginCmd) throws Exception{
        return WrapperResponse.success(userAccountCmdService.login(merchantLoginCmd));
    }

    @ApiOperation(httpMethod = "POST", value = "登录")
    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse test() {
        UserAuthDto userAuthDto = UserIdUtils.getUser();
        return WrapperResponse.success(JSON.toJSONString(userAuthDto));
    }
}
