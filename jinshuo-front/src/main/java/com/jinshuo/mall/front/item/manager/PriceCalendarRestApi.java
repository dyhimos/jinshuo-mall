package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.PriceCalendarMainCmd;
import com.jinshuo.mall.service.item.service.command.PriceCalendarComService;
import com.jinshuo.mall.service.item.service.query.PriceCalendarQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/10/10.
 */
@RestController
@Api(description = "价格日历管理")
@RequestMapping("/v1/manager/pricecalendar")
public class PriceCalendarRestApi {

    @Autowired
    private PriceCalendarComService priceCalendarComService;

    @Autowired
    private PriceCalendarQueryService priceCalendarQueryService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "保存价格日历")
    public WrapperResponse saveService(@Valid @RequestBody PriceCalendarMainCmd cmd) {
        return WrapperResponse.success(priceCalendarComService.create(cmd));
    }

    @GetMapping("/get/{skuId}")
    @ApiOperation(httpMethod = "GET", value = "根据skuId查询价格日历")
    public WrapperResponse saveService(@RequestParam Long skuId) {
        return WrapperResponse.success(priceCalendarQueryService.getById(skuId));
    }
}
