package com.jinshuo.mall.front.item.manager;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.BrandCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.BrandUpdateCmd;
import com.jinshuo.mall.service.item.application.cmd.CommId;
import com.jinshuo.mall.service.item.application.qry.BrandQry;
import com.jinshuo.mall.service.item.service.command.BrandComService;
import com.jinshuo.mall.service.item.service.query.BrandQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/19.
 */
@RestController
@Api(description = "品牌管理接口")
@RequestMapping("/v1/manager/brand")
public class BrandRestApi {

    @Autowired
    private BrandComService brandComService;

    @Autowired
    private BrandQueryService brandQueryService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "创建品牌")
    public WrapperResponse create(@Valid @RequestBody BrandCreateCmd cmd) {
        brandComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改品牌")
    public WrapperResponse get(@Valid @RequestBody BrandUpdateCmd cmd) {
        brandComService.update(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/get")
    @ApiOperation(httpMethod = "GET", value = "查询品牌集合")
    public WrapperResponse get() {
        return WrapperResponse.success(brandQueryService.getAll());
    }


    @PostMapping("/delete")
    @ApiOperation(httpMethod = "POST", value = "删除品牌")
    public WrapperResponse delete(@Valid @RequestBody CommId cmd) {
        return WrapperResponse.success(brandComService.delete(cmd.getId()));
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询品牌集合")
    public WrapperResponse<PageInfo> getByPage(@RequestBody BrandQry qry) {
        return WrapperResponse.success(brandQueryService.getCategorysByPage(qry));
    }

}
