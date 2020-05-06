package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.dto.MyUpgradeGapDto;
import com.jinshuo.mall.service.user.application.qry.DisSalemanQry;
import com.jinshuo.mall.service.user.service.query.MemberQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dyh on 2019/9/2.
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/wx/dis")
@Api(description = "我的客户")
public class WxDisRestApi {

    @Autowired
    private MemberQueryService memberQueryService;

    @ApiOperation(httpMethod = "POST", value = "获取我的客户")
    @PostMapping(value = "/getMySubordinateInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMySubordinateInfo(@RequestBody DisSalemanQry qry) {
        return WrapperResponse.success(memberQueryService.getMySubordinateInfo(qry));
    }

    @ApiOperation(httpMethod = "GET", value = "获取我的粉丝数")
    @GetMapping(value = "/getMyFans", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMyFans() {
        return WrapperResponse.success(memberQueryService.getMyFans());
    }

    @ApiOperation(httpMethod = "GET", value = "获取我的升级距离")
    @GetMapping(value = "/getGap", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getGap() {
        MyUpgradeGapDto dto = null;
        try {
            dto = memberQueryService.getGap();
        } catch (Exception e) {
            log.error("获取我的升级距离失败", e);
            e.printStackTrace();
        }
        return WrapperResponse.success(dto);
    }
}
