package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.TagCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.TagUpdateCmd;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.service.command.TagComService;
import com.jinshuo.mall.service.item.service.query.TagQueryService;
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
@RequestMapping("/v1/manager/tag")
public class TagRestApi {

    @Autowired
    private TagComService tagComService;

    @Autowired
    private TagQueryService tagQueryService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "创建标签")
    public WrapperResponse create(@Valid @RequestBody TagCreateCmd cmd) {
        tagComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改标签")
    public WrapperResponse get(@Valid @RequestBody TagUpdateCmd cmd) {
        tagComService.update(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/get")
    @ApiOperation(httpMethod = "GET", value = "获取查询标签集合")
    public WrapperResponse get() {
        return WrapperResponse.success(tagQueryService.getAll());
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除标签")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(tagComService.delete(id));
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询标签")
    public WrapperResponse list(@RequestBody TagQry qry) {
        return WrapperResponse.success(tagQueryService.getByPage(qry));
    }


}
