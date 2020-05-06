package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.SupplierCmd;
import com.jinshuo.mall.service.user.application.cmd.SupplierManagerCmd;
import com.jinshuo.mall.service.user.application.qry.SupplierQry;
import com.jinshuo.mall.service.user.service.command.SupplierComService;
import com.jinshuo.mall.service.user.service.command.SupplierManagerComService;
import com.jinshuo.mall.service.user.service.query.SupplierManagerQueryService;
import com.jinshuo.mall.service.user.service.query.SupplierQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/8/16.
 */
@RestController
@Validated
@RequestMapping("/v1/manager/supplier")
@Api(description = "供应商查询")
public class SupplierRestApi {

    @Autowired
    private SupplierComService supplierComService;

    @Autowired
    private SupplierQueryService supplierQueryService;

    @Autowired
    private SupplierManagerComService supplierManagerComService;

    @Autowired
    private SupplierManagerQueryService supplierManagerQueryService;

    @ApiOperation(httpMethod = "POST", value = "添加供应商")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse create(@Validated @RequestBody SupplierCmd cmd) {
        return WrapperResponse.success(supplierComService.save(cmd));
    }


    @ApiOperation(httpMethod = "POST", value = "分页查询供应商")
    @PostMapping(value = "/getByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getByPage(@RequestBody SupplierQry qry) {
        return WrapperResponse.success(supplierQueryService.getByPage(qry));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("根据id查询供应商")
    public WrapperResponse getById(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(supplierQueryService.getById(id));
    }


    @ApiOperation(httpMethod = "GET", value = "查询供应商集合")
    @GetMapping(value = "/getByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getByPage() {
        return WrapperResponse.success(supplierQueryService.getList());
    }


    @GetMapping("/delete/{id}")
    @ApiOperation("删除供应商")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(supplierComService.delete(id));
    }


    @ApiOperation(httpMethod = "POST", value = "添加供应商管理员")
    @PostMapping(value = "/createManager", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse createManager(@Validated @RequestBody SupplierManagerCmd cmd) {
        return WrapperResponse.success(supplierManagerComService.save(cmd));
    }

    @ApiOperation(httpMethod = "GET", value = "获取供应商管理员列表")
    @GetMapping(value = "/getSupplierManager/{supplierId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getSupplierManager(@PathVariable(value = "supplierId") Long supplierId) {
        return WrapperResponse.success(supplierManagerQueryService.getBySupplierId(supplierId));
    }

    @ApiOperation(httpMethod = "GET", value = "删除供应商管理员")
    @GetMapping(value = "/deleteSupplierManager/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse deleteSupplierManager(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(supplierManagerComService.delete(id));
    }
}
