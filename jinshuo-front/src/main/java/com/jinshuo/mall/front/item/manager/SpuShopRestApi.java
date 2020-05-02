package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.SpuShopCmd;
import com.jinshuo.mall.service.item.service.command.SpuShopComService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/24.
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/manager/spuShop")
@Api(description = "店铺商品管理接口")
public class SpuShopRestApi {
    @Autowired
    private SpuShopComService spuShopComService;

    @PostMapping("/addSpu")
    @ApiOperation(httpMethod = "POST", value = "创建店铺商品")
    public WrapperResponse create(@Valid @RequestBody SpuShopCmd cmd) {
        spuShopComService.create(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/delete/{shopId}/{spuId}")
    @ApiOperation(httpMethod = "GET", value = "创建店铺商品")
    public WrapperResponse delete(@PathVariable(value = "shopId") Long shopId, @PathVariable(value = "spuId") Long spuId) {
        spuShopComService.delete(shopId,spuId);
        return WrapperResponse.success();
    }
}
