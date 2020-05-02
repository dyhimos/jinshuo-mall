package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.*;
import com.jinshuo.mall.service.item.application.qry.SpecQry;
import com.jinshuo.mall.service.item.service.command.SpecComService;
import com.jinshuo.mall.service.item.service.command.SpecOptionComService;
import com.jinshuo.mall.service.item.service.query.SpecQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/17.
 */
@RestController
@Api(description = "规格参数管理接口")
@RequestMapping("/v1/manager/spec")
public class SpecRestApi {

    @Autowired
    private SpecComService specComService;

    @Autowired
    private SpecQueryService specQueryService;

    @Autowired
    private SpecOptionComService specOptionComService;


    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "单独保存规格（含参数）")
    public WrapperResponse create(@Valid @RequestBody SpecCreateCmd cmd) {
        specComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "单独修改规格")
    public WrapperResponse update(@Valid @RequestBody SpecUpdateCmd cmd) {
        specComService.update(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/getSpec")
    @ApiOperation(httpMethod = "POST", value = "获取规格集合")
    public WrapperResponse getSpec(@Valid @RequestBody SpecQry qry) {
        return WrapperResponse.success(specQueryService.getSpecList(qry));
    }


    @PostMapping("/createSpec")
    @ApiOperation(httpMethod = "POST", value = "单独保存规格")
    public WrapperResponse createSpec(@Valid @RequestBody SpecCreateCmd cmd) {
        return WrapperResponse.success(specComService.createSpec(cmd).getSpecId().getId());
    }

    @PostMapping("/createOption")
    @ApiOperation(httpMethod = "POST", value = "保存规格参数")
    public WrapperResponse createOption(@Valid @RequestBody SpecOptionAloneCreateCmd cmd) {
        specOptionComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/updateOption")
    @ApiOperation(httpMethod = "POST", value = "修改规格参数")
    public WrapperResponse updateOption(@Valid @RequestBody SpecOptionAloneUpdateCmd cmd) {
        specOptionComService.update(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/deleteOption")
    @ApiOperation(httpMethod = "POST", value = "删除规格参数")
    public WrapperResponse deleteOption(@Valid @RequestBody CommId cmd) {
        specOptionComService.deleteOption(cmd.getId());
        return WrapperResponse.success();
    }

    @PostMapping("/delete")
    @ApiOperation(httpMethod = "POST", value = "删除规格")
    public WrapperResponse delete(@Valid @RequestBody CommId cmd) {
        specComService.delete(cmd.getId());
        return WrapperResponse.success();
    }


}
