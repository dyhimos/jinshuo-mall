package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.AddressCmd;
import com.jinshuo.mall.service.user.application.cmd.AddressCreateCmd;
import com.jinshuo.mall.service.user.application.qry.AddressQry;
import com.jinshuo.mall.service.user.service.command.AddressComService;
import com.jinshuo.mall.service.user.service.query.AddressQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/8/16.
 */
@RestController
@Validated
@RequestMapping("/v1/wx/address")
@Api(description = "我的常用联系地址")
public class WxAddressRestApi {

    @Autowired
    private AddressComService addressComService;
    @Autowired
    private AddressQueryService addressQueryService;

    @ApiOperation(httpMethod = "POST", value = "添加地址")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse create(@Validated @RequestBody AddressCreateCmd cmd) {
        return WrapperResponse.success(addressComService.save(cmd));
    }

    @ApiOperation(httpMethod = "POST", value = "修改地址")
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse update(@Validated @RequestBody AddressCmd cmd) {
        return WrapperResponse.success(addressComService.update(cmd));
    }

    @ApiOperation(httpMethod = "POST", value = "分页查询我常用地址")
    @PostMapping(value = "/getByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getByPage(@RequestBody AddressQry qry) {
        return WrapperResponse.success(addressQueryService.getByPage(qry));
    }

    @ApiOperation(httpMethod = "GET", value = "查询我的默认常用地址")
    @GetMapping(value = "/getDefault", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getDefault() {
        return WrapperResponse.success(addressQueryService.getDefault());
    }


    @ApiOperation(httpMethod = "GET", value = "查询我的默认常用地址")
    @GetMapping(value = "/getAddressByid/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getAddressByid(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(addressQueryService.getAddressById(id));
    }
}
