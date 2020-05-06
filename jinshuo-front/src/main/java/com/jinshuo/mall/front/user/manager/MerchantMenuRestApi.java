package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.BusinessTypeMenuCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.service.command.BusinessTypeMenuComService;
import com.jinshuo.mall.service.user.service.command.MeunCmdService;
import com.jinshuo.mall.service.user.service.query.MenuQueryService;
import com.jinshuo.mall.service.user.service.query.MerchantMenuQueryService;
import com.jinshuo.mall.service.user.service.query.MerchantQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/9/20.
 */
@RestController
@Validated
@RequestMapping("/v1/manager/merchantMenu")
@Api(description = "我的菜单")
public class MerchantMenuRestApi {

    @Autowired
    private MerchantMenuQueryService merchantMenuQueryService;

    @Autowired
    private MenuQueryService menuQueryService;

    @Autowired
    private MerchantQueryService merchantQueryService;

    @Autowired
    private BusinessTypeMenuComService businessTypeMenuComService;


    @Autowired
    private MeunCmdService meunCmdService;

    @ApiOperation(httpMethod = "GET", value = "根据店铺id查询店铺的菜单")
    @GetMapping(value = "/getMyMenu/{shopId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMyMenu(@PathVariable(value = "shopId") Long shopId) {
        ShopIdCmd shopIdCmd = new ShopIdCmd();
        shopIdCmd.setShopId(shopId);
        return WrapperResponse.success(meunCmdService.queryMenuByShop(shopIdCmd));
        //return WrapperResponse.success(merchantMenuQueryService.getByShopId(shopId));
    }


    @ApiOperation(httpMethod = "GET", value = "查询所有菜单")
    @GetMapping(value = "/getAllMenu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getAllMenu() {
        return WrapperResponse.success(menuQueryService.getMenuTree());
    }

    @ApiOperation(httpMethod = "GET", value = "根据id查询商家信息")
    @GetMapping(value = "/getMyMerchant/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getMyMerchant(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(merchantQueryService.getById(id));
    }

    @ApiOperation(httpMethod = "GET", value = "根据userId查询商家信息")
    @GetMapping(value = "/getByUserId/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getByUserId(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(merchantQueryService.findByUserId(id));
    }


    @ApiOperation(httpMethod = "POST", value = "设置业务类型默认的菜单")
    @PostMapping(value = "/saveBusinessTypeMenu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse saveBusinessTypeMenu(@RequestBody BusinessTypeMenuCmd cmd) {
        return WrapperResponse.success(businessTypeMenuComService.save(cmd));
    }

    @ApiOperation(httpMethod = "GET", value = "删除业务类型默认的菜单")
    @GetMapping(value = "/deleteBusinessTypeMenu/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse delete(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(businessTypeMenuComService.delete(id));
    }

    @ApiOperation(httpMethod = "GET", value = "根据业务类型查询默认的菜单")
    @GetMapping(value = "/getByBusinessTypeId/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse getByBusinessTypeId(@PathVariable(value = "id") Integer id) {
        return WrapperResponse.success(businessTypeMenuComService.getByBusinessTypeId(id));
    }

}
