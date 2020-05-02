package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.SpuOtherCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuOtherUpdateCmd;
import com.jinshuo.mall.service.item.service.command.SpuOtherComService;
import com.jinshuo.mall.service.item.service.query.SpuOtherQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/19.
 */
@RestController
@Api(description = "商品其他设置接口")
@RequestMapping("/v1/manager/spuother")
public class SpuOtherRestApi {

    @Autowired
    private SpuOtherComService spuOtherComService;

    @Autowired
    private SpuOtherQueryService spuOtherQueryService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "新增商品其他设置")
    public WrapperResponse create(@Valid @RequestBody SpuOtherCreateCmd cmd) {
        spuOtherComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改商品其他设置")
    public WrapperResponse get(@Valid @RequestBody SpuOtherUpdateCmd cmd) {
        spuOtherComService.update(cmd);
        return WrapperResponse.success();
    }

    /*@GetMapping("/getSpuOther")
    @ApiOperation(httpMethod = "GET", value = "查询商品其他设置")
    public WrapperResponse getSpuOther() {
        return WrapperResponse.success(spuOtherQueryService.getAll());
    }*/

    @GetMapping("/getBySpuId/{spuId}")
    @ApiOperation(httpMethod = "GET", value = "根据商品spuId查询商品其他设置")
    public WrapperResponse getBySpuId(@PathVariable(value = "spuId") Long spuId) {
        return WrapperResponse.success(spuOtherQueryService.getBySpuId(spuId));
    }

}
