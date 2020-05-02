package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.ServiceSupportCmd;
import com.jinshuo.mall.service.item.service.command.ServiceSupportComService;
import com.jinshuo.mall.service.item.service.query.ServiceSupportQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/30.
 */
@RestController
@Api(description = "产品服务")
@RequestMapping("/v1/manager/service")
public class ServiceRestApi {

    @Autowired
    private ServiceSupportComService serviceSupportComService;

    @Autowired
    private ServiceSupportQueryService serviceSupportQueryService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "保存服务")
    public WrapperResponse saveService(@Valid @RequestBody ServiceSupportCmd cmd) {
        return WrapperResponse.success(serviceSupportComService.create(cmd));
    }


    @PostMapping("/getList")
    @ApiOperation(httpMethod = "POST", value = "获取保存集合")
    public WrapperResponse getList(@Valid @RequestBody ServiceSupportCmd cmd) {
        return WrapperResponse.success(serviceSupportQueryService.getServices());
    }
}
