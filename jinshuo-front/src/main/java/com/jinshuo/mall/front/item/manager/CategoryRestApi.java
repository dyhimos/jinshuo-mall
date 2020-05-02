package com.jinshuo.mall.front.item.manager;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.item.category.model.Category;
import com.jinshuo.mall.service.item.application.cmd.CategoryCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CategoryUpdateCmd;
import com.jinshuo.mall.service.item.application.dto.CategoryDto;
import com.jinshuo.mall.service.item.application.qry.CategoryQry;
import com.jinshuo.mall.service.item.service.command.CategoryComService;
import com.jinshuo.mall.service.item.service.query.CategoryQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 19458 on 2019/7/3.
 */
@RestController
@Api(description = "类目管理接口")
@RequestMapping("/v1/manager/category")
public class CategoryRestApi {

    @Autowired
    private CategoryComService categoryService;

    @Autowired
    private CategoryQueryService queryService;


    @PostMapping("/create")
    @ApiOperation(value = "创建类别")
    public WrapperResponse<Category> create(@Valid @RequestBody CategoryCreateCmd dto) {
        return WrapperResponse.success(categoryService.create(dto));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改类别")
    public WrapperResponse<Category> update(@Valid @RequestBody CategoryUpdateCmd dto) {
        categoryService.update(dto);
        return WrapperResponse.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除类别")
    public WrapperResponse<Category> delete(@PathVariable(value = "id") String id) {
        categoryService.delete(Long.parseLong(id));
        return WrapperResponse.success();
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询规格集合")
    public WrapperResponse<PageInfo> getByPage(@RequestBody CategoryQry qry) {
        return WrapperResponse.success(queryService.getCategorysByPage(qry));
    }

    @PostMapping("/getCategorys")
    @ApiOperation(value = "查询类目集合")
    public WrapperResponse<List<CategoryDto>> getCategorys(@RequestBody CategoryQry qry) {
        //log.info("------------------" + queryService.getCategorys(qry).toString());
        return WrapperResponse.success(queryService.getCategorys(qry));
    }

}
