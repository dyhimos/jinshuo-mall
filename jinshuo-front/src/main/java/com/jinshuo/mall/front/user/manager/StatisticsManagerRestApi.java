package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.qry.StatisticsQry;
import com.jinshuo.mall.service.user.application.qry.VisitorCountQry;
import com.jinshuo.mall.service.user.service.command.StatisticsComService;
import com.jinshuo.mall.service.user.service.query.StatisticsQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/4/2.
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/manager/statistics")
@Api(description = "统计访问量")
public class StatisticsManagerRestApi {

    @Autowired
    private StatisticsComService statisticsComService;

    @Autowired
    private StatisticsQueryService statisticsQueryService;


    @ApiOperation(httpMethod = "POST", value = "统计访问量")
    @PostMapping(value = "/countVisitor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse countVisitor(@RequestBody VisitorCountQry qry) {
        return WrapperResponse.success(statisticsQueryService.countVisitor(qry));
    }


    @ApiOperation(httpMethod = "POST", value = "统计转换率")
    @PostMapping(value = "/conversionRate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse conversionRate(@RequestBody VisitorCountQry qry) {
        return WrapperResponse.success(statisticsQueryService.conversionRate(qry));
    }




    @ApiOperation(httpMethod = "POST", value = "统计访问量列表")
    @PostMapping(value = "/countVisitorList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse countVisitorList(@RequestBody StatisticsQry qry) {
        return WrapperResponse.success(statisticsQueryService.countVisitorList(qry));
    }


    @ApiOperation(httpMethod = "POST", value = "统计转换率列表")
    @PostMapping(value = "/conversionRateList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse conversionRateList(@RequestBody StatisticsQry qry) {
        return WrapperResponse.success(statisticsQueryService.conversionRateList(qry));
    }
}
