package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.CategoryParameterCmd;
import com.jinshuo.mall.service.item.application.cmd.CommId;
import com.jinshuo.mall.service.item.application.cmd.ParameterCmd;
import com.jinshuo.mall.service.item.application.cmd.ParameterValueCmd;
import com.jinshuo.mall.service.item.service.command.CategoryParameterComService;
import com.jinshuo.mall.service.item.service.command.ParameterComService;
import com.jinshuo.mall.service.item.service.command.ParameterValueComService;
import com.jinshuo.mall.service.item.service.command.SpuParameterComService;
import com.jinshuo.mall.service.item.service.query.ParameterQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/19.
 */
@RestController
@Api(description = "产品参数管理接口")
@RequestMapping("/v1/manager/parameter")
public class ParameterRestApi {

    @Autowired
    private ParameterComService parameterComService;

    @Autowired
    private ParameterValueComService parameterValueComService;

    @Autowired
    private ParameterQueryService parameterQueryService;

    @Autowired
    private SpuParameterComService spuParameterComService;

    @Autowired
    private CategoryParameterComService categoryParameterComService;


    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "保存参数")
    public WrapperResponse create(@Valid @RequestBody ParameterCmd cmd) {
        parameterComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/createParam")
    @ApiOperation(httpMethod = "POST", value = "保存参数值")
    public WrapperResponse createParam(@Valid @RequestBody ParameterValueCmd cmd) {
        return WrapperResponse.success(parameterValueComService.create(cmd));
    }

    @PostMapping("/deleteParam")
    @ApiOperation(httpMethod = "POST", value = "删除参数")
    public WrapperResponse deleteParam(@Valid @RequestBody CommId cmd) {
        return WrapperResponse.success(parameterComService.delete(cmd.getId()));
    }

    @PostMapping("/deleteParamValue")
    @ApiOperation(httpMethod = "POST", value = "删除参数值")
    public WrapperResponse delete(@Valid @RequestBody CommId cmd) {
        return WrapperResponse.success(parameterValueComService.delete(cmd.getId()));
    }


    @GetMapping("/getParamsByShopId/{shopId}")
    @ApiOperation(httpMethod = "GET", value = "获取参数集合")
    public WrapperResponse getParamsByShopId(@PathVariable(value = "shopId") Long shopId) {
        return WrapperResponse.success(parameterQueryService.getAllByShopId(shopId));
    }

    @GetMapping("/getParameterId/{parameterId}")
    @ApiOperation(httpMethod = "GET", value = "获取参数详情")
    public WrapperResponse getParameterId(@PathVariable(value = "parameterId") Long parameterId) {
        return WrapperResponse.success(parameterQueryService.getAllByParameterId(parameterId));
    }


    @PostMapping("/saveCategoryId")
    @ApiOperation(httpMethod = "GET", value = "保存类目与参数关系")
    public WrapperResponse saveCategoryId(CategoryParameterCmd cmd) {
        categoryParameterComService.save(cmd);
        return WrapperResponse.success();
    }

    /*@PostMapping("/saveParam")
    @ApiOperation(httpMethod = "POST", value = "保存产品参数")
    public WrapperResponse saveParam(@RequestBody List<SpuParamCmd> cmdList) {
        return WrapperResponse.success(spuParameterComService.saveList(cmdList));
    }*/


}
