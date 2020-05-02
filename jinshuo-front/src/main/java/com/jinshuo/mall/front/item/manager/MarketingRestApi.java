package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.MarketingCmd;
import com.jinshuo.mall.service.item.application.qry.MarketingQry;
import com.jinshuo.mall.service.item.service.command.MarketingComService;
import com.jinshuo.mall.service.item.service.command.MarketingProdutComService;
import com.jinshuo.mall.service.item.service.query.MarketingQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/10/16.
 */
@RestController
@Api(description = "活动管理接口")
@RequestMapping("/v1/manager/marketing")
public class MarketingRestApi {

    @Autowired
    private MarketingComService marketingComService;

    @Autowired
    private MarketingQueryService marketingQueryService;

    @Autowired
    private MarketingProdutComService marketingProdutComService;


    @PostMapping("/saveMarketing")
    @ApiOperation(httpMethod = "POST", value = "保存活动")
    public WrapperResponse saveMarketing(@Valid @RequestBody MarketingCmd cmd) {
        marketingComService.create(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/deleteMarketing/{id}")
    @ApiOperation(httpMethod = "GET", value = "删除活动")
    public WrapperResponse deleteMarketing(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(marketingComService.delete(id));
    }

    @PostMapping("/getMarketingByPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询活动")
    public WrapperResponse getMarketingByPage(@RequestBody MarketingQry qry) {
        return WrapperResponse.success(marketingQueryService.getPageInfo(qry));
    }

    @GetMapping("/getMarketingInfo/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据活动ID查询活动详情")
    public WrapperResponse getMarketingInfo(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(marketingQueryService.getById(id));
    }

    @GetMapping("/deleteMarketingProduct/{id}")
    @ApiOperation(httpMethod = "GET", value = "删除活动产品")
    public WrapperResponse deleteMarketingProduct(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(marketingProdutComService.delete(id));
    }

}
