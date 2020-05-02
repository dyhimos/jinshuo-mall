package com.jinshuo.mall.front.item.manager;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.CouponCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CouponReceiveCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.CouponUpdateCmd;
import com.jinshuo.mall.service.item.application.qry.CouponQry;
import com.jinshuo.mall.service.item.service.command.CouponComService;
import com.jinshuo.mall.service.item.service.command.CouponItemComService;
import com.jinshuo.mall.service.item.service.command.CouponReceiveComService;
import com.jinshuo.mall.service.item.service.query.CouponQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/30.
 */
@Slf4j
@RestController
@Api(description = "优惠券管理接口")
@RequestMapping("/v1/manager/coupon")
public class CouponRestApi {

    @Autowired
    private CouponComService couponComService;

    @Autowired
    private CouponQueryService couponQueryService;

    @Autowired
    private CouponItemComService couponItemComService;

    @Autowired
    private CouponReceiveComService couponReceiveComService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "创建优惠券")
    public WrapperResponse create(@Valid @RequestBody CouponCreateCmd cmd) {
        couponComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改优惠券")
    public WrapperResponse get(@Valid @RequestBody CouponUpdateCmd cmd) {
        couponComService.update(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除优惠券")
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(couponComService.delete(id));
    }

    @GetMapping("/deleteItem/{id}")
    @ApiOperation(value = "删除优惠券适用产品")
    public WrapperResponse deleteItem(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(couponItemComService.delete(id));
    }

    @PostMapping("/getByPage")
    @ApiOperation(value = "分页查询优惠券")
    public WrapperResponse list(@RequestBody CouponQry qry) {
        return WrapperResponse.success(couponQueryService.getDtoByPage(qry));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据id查询优惠券详情")
    public WrapperResponse getById(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(couponQueryService.getDtoById(id));
    }

    @ApiOperation(httpMethod = "POST", value = "后台发送卷")
    @PostMapping(value = "/autoCollar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse autoCollar(@Valid @RequestBody CouponReceiveCreateCmd cmd) {
        log.info(" -- 后台发送优惠券,输入参数：" + JSONObject.toJSONString(cmd));
        return WrapperResponse.success(couponReceiveComService.managerCreate(cmd));
    }
}
