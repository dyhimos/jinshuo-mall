package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.ShopCmd;
import com.jinshuo.mall.service.user.service.command.ShopComService;
import com.jinshuo.mall.service.user.service.query.ShopQueryService;
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
@RequestMapping("/v1/manager/shop")
@Api(description = "店铺管理")
public class ShopRestApi {

    @Autowired
    private ShopQueryService shopQueryService;

    @Autowired
    private ShopComService shopComService;


    @ApiOperation(httpMethod = "GET", value = "查询店铺集合")
    @GetMapping(value = "/getByPage/{merchantId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getByPage(@PathVariable(value = "merchantId") Long merchantId) {
        return WrapperResponse.success(shopQueryService.getList(merchantId));
    }

    @ApiOperation(httpMethod = "GET", value = "查询店铺详情")
    @GetMapping(value = "/queryDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse queryDetail(@RequestParam("id") Long id) {
        return WrapperResponse.success(shopQueryService.queryDetail(id));
    }


    @ApiOperation(httpMethod = "POST", value = "保存店铺")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse create(@Validated @RequestBody ShopCmd cmd) {
        return WrapperResponse.success(shopComService.save(cmd));
    }


}
