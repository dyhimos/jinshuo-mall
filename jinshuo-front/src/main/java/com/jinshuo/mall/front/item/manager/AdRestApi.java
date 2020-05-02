package com.jinshuo.mall.front.item.manager;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.AdPositionCmd;
import com.jinshuo.mall.service.item.application.cmd.AdvertisementCmd;
import com.jinshuo.mall.service.item.application.qry.AdQry;
import com.jinshuo.mall.service.item.service.command.AdPositionComService;
import com.jinshuo.mall.service.item.service.command.AdvertisementComService;
import com.jinshuo.mall.service.item.service.query.AdPositionQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/10.
 */
@RestController
@Api(description = "广告管理接口")
@RequestMapping("/v1/manager/ad")
public class AdRestApi {

    @Autowired
    private AdPositionComService adPositionComService;

    @Autowired
    private AdPositionQueryService adPositionQueryService;

    @Autowired
    private AdvertisementComService advertisementComService;


    @PostMapping("/saveAdPosition")
    @ApiOperation(httpMethod = "POST", value = "添加广告位")
    public WrapperResponse createAdPosition(@Valid @RequestBody AdPositionCmd cmd) {
        adPositionComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/queryAdPositionPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询广告位")
    public WrapperResponse queryAdPositionPage(@RequestBody AdQry qry) {
        PageInfo pageInfo = adPositionQueryService.getPageInfo(qry);
        return WrapperResponse.success(pageInfo);
    }


    @PostMapping("/saveAd")
    @ApiOperation(httpMethod = "POST", value = "添加广告")
    public WrapperResponse createAd(@Valid @RequestBody AdvertisementCmd cmd) {
        advertisementComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/queryAdByPositionId")
    @ApiOperation(httpMethod = "POST", value = "根据广告位id查询广告")
    public WrapperResponse queryAdByPositionId(@RequestBody AdQry qry) {
        return WrapperResponse.success(adPositionQueryService.queryAdByPositionId(qry));
    }

    @GetMapping("/deleteAd/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据广告id删除广告")
    public WrapperResponse deleteAd(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(advertisementComService.deleteAd(id));
    }

    @GetMapping("/deleteAdPosition/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据广告位id删除广告位")
    public WrapperResponse deleteAdPosition(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(adPositionComService.deleteAdPosition(id));
    }

    @GetMapping("/deleteAdPosition/{id}/{adStatus}")
    @ApiOperation(httpMethod = "GET", value = "修改广告位状态")
    public WrapperResponse deleteAdPosition(@PathVariable(value = "id") Long id, @PathVariable(value = "adStatus") Integer adStatus) {
        return WrapperResponse.success(adPositionComService.updateStatus(id, adStatus));
    }
}
