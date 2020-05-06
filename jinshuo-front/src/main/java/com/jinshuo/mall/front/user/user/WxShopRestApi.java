package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.qry.ProblemQry;
import com.jinshuo.mall.service.user.service.command.ProblemComService;
import com.jinshuo.mall.service.user.service.query.ShopQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/v1/wx/shop")
@Api(description = " 店铺信息")
public class WxShopRestApi {


    @Autowired
    private ShopQueryService shopQueryService;

    @Autowired
    private ProblemComService problemComService;

    @ApiOperation(httpMethod = "GET", value = "查询店铺详情")
    @GetMapping(value = "/queryDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse queryDetail(@RequestParam("id") Long id) {
        return WrapperResponse.success(shopQueryService.queryDetail(id));
    }

    /**
     * 查询常见问题
     *
     * @param cmd
     * @return
     */
    @PostMapping("/queryProblems")
    @ApiOperation("查询常见问题")
    public WrapperResponse queryProblems(@RequestBody ProblemQry cmd) {
        return WrapperResponse.success(problemComService.queryProblems(cmd));
    }

    @GetMapping("/queryProblemDetail/{id}")
    @ApiOperation("查询常见问题详情")
    public WrapperResponse queryProblemDetail(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(problemComService.queryDetail(id));
    }
}

	
