package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.TypeCreateCmd;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.service.command.TypeComService;
import com.jinshuo.mall.service.item.service.query.TypeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/19.
 */
@RestController
@Api(description = "标签管理接口")
@RequestMapping("/v1/manager/type")
public class TypeRestApi {

    @Autowired
    private TypeComService typeComService;

    @Autowired
    private TypeQueryService typeQueryService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "创建类型")
    public WrapperResponse create(@Valid @RequestBody TypeCreateCmd cmd) {
        typeComService.create(cmd);
        return WrapperResponse.success();
    }

  /*  @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改类型")
    public WrapperResponse get(@Valid @RequestBody TagUpdateCmd cmd) {
        tagComService.update(cmd);
        return WrapperResponse.success();
    }*/

    @GetMapping("/get")
    @ApiOperation(httpMethod = "GET", value = "获取查询标签集合")
    public WrapperResponse get() {
        return WrapperResponse.success(typeQueryService.getAll());
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除类型")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(typeComService.delete(id));
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询标签")
    public WrapperResponse list(@RequestBody TagQry qry) {
        return WrapperResponse.success(typeQueryService.getByPage(qry));
    }


}
