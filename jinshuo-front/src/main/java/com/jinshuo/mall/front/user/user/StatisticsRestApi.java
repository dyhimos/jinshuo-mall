package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.core.utils.IpUtil;
import com.jinshuo.mall.service.user.application.cmd.StatisticsCmd;
import com.jinshuo.mall.service.user.service.command.StatisticsComService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2020/4/2.
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/wx/statistics")
@Api(description = "统计访问量")
public class StatisticsRestApi {

    @Autowired
    private StatisticsComService statisticsComService;


    @ApiOperation(httpMethod = "POST", value = "统计访问量")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse create(@RequestBody StatisticsCmd cmd, HttpServletRequest request) {
        statisticsComService.saveStatistics(cmd, request);
        return WrapperResponse.success();
    }


    @ApiOperation(httpMethod = "POST", value = "统计访问量")
    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse create( HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        System.out.println("============"+ipAddr);
        return WrapperResponse.success();
    }
}
