package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.CityCmd;
import com.jinshuo.mall.service.item.service.command.CityComService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/19.
 */
@RestController
@Api(description = "开通城市（区域）管理接口")
@RequestMapping("/v1/manager/city")
public class AreaRestApi {

    @Autowired
    private CityComService cityComService;


    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "开通区域")
    public WrapperResponse create(@Valid @RequestBody CityCmd cmd) {
        cityComService.create(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(httpMethod = "GET", value = "关闭区域")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(cityComService.delete(id));
    }

    @GetMapping("/getCityList/{shopId}")
    @ApiOperation(value = "根据shopId查询开通区域")
    public WrapperResponse getCityList(@PathVariable(value = "shopId") Long shopId) {
        return WrapperResponse.success(cityComService.getAll(shopId));
    }

    @GetMapping("/getCityTree/{shopId}")
    @ApiOperation(value = "根据shopId查询开通区域按层级")
    public WrapperResponse getCityTree(@PathVariable(value = "shopId") Long shopId) {
        return WrapperResponse.success(cityComService.getCityTree(shopId));
    }

}
