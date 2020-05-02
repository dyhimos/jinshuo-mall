package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.LatticeCmd;
import com.jinshuo.mall.service.item.application.qry.LatticeQry;
import com.jinshuo.mall.service.item.service.command.LatticeComService;
import com.jinshuo.mall.service.item.service.query.LatticeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/24.
 */
@RestController
@Api(description = "菜单格子管理")
@RequestMapping("/v1/manager/lattice")
public class LatticeRestApi {

    @Autowired
    private LatticeComService latticeComService;

    @Autowired
    private LatticeQueryService latticeQueryService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "保存菜单格子")
    public WrapperResponse create(@Valid @RequestBody LatticeCmd cmd) {
        return WrapperResponse.success(latticeComService.create(cmd));
    }

    @PostMapping("/updateShow")
    @ApiOperation(httpMethod = "POST", value = "修改展示状态")
    public WrapperResponse updateShow(@RequestBody LatticeCmd cmd) {
        return WrapperResponse.success(latticeComService.updateShow(cmd));
    }

    @PostMapping("/getByPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询菜单格子")
    public WrapperResponse create(LatticeQry qry) {
        return WrapperResponse.success(latticeQueryService.getPageInfo(qry));
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(httpMethod = "GET", value = "删除菜单格子")
    public WrapperResponse updateShow(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(latticeComService.delete(id));
    }
}
