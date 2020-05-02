package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.annotation.SysLog;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.GoodUnitCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.GoodUnitUpdateCmd;
import com.jinshuo.mall.service.item.application.qry.UnitQry;
import com.jinshuo.mall.service.item.service.command.GoodUnitComService;
import com.jinshuo.mall.service.item.service.query.GoodUnitQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/8.
 */
@RestController
@Api(description = "单位管理接口")
@RequestMapping("/v1/manager/unit")
public class UnitRestApi {

    @Autowired
    private GoodUnitComService unitService;

    @Autowired
    private GoodUnitQueryService goodUnitQueryService;


    @PostMapping("/create")
    @ApiOperation(value = "创建单位")
    @SysLog(value = "添加产品", ignore = true)
    public WrapperResponse create(@Valid @RequestBody GoodUnitCreateCmd dto) {
        unitService.createUnit(dto);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改类别")
    public WrapperResponse update(@Valid @RequestBody GoodUnitUpdateCmd dto) {
        unitService.updateUnit(dto);
        return WrapperResponse.success();
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询规格集合")
    public WrapperResponse list(@RequestBody UnitQry qry) {
        return WrapperResponse.success(goodUnitQueryService.findUnits(qry));
    }


    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除单位")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(unitService.deleteUnit(id));
    }


    @GetMapping("/getByUnitList")
    @ApiOperation(value = "查询单位集合")
    public WrapperResponse getByUnitList() {
        return WrapperResponse.success(goodUnitQueryService.findUnits());
    }

}
