package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.CollectCmd;
import com.jinshuo.mall.service.user.application.qry.CollectQry;
import com.jinshuo.mall.service.user.service.command.CollectComService;
import com.jinshuo.mall.service.user.service.query.CollectQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/8/1.
 */
@RestController
@Validated
@RequestMapping("/v1/wx/collect")
@Api(description = "我的收藏夹")
public class WxGoodsCollectRestApi {

    @Autowired
    private CollectComService collectComService;

    @Autowired
    private CollectQueryService collectQueryService;

    @ApiOperation(httpMethod = "POST", value = "添加收藏")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse addCollect(@Validated @RequestBody CollectCmd cmd) {
        return WrapperResponse.success(collectComService.create(cmd));
    }

    @ApiOperation(httpMethod = "GET", value = "删除收藏")
    @GetMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse deleteCollect(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(collectComService.delete(id));
    }

    @ApiOperation(httpMethod = "POST", value = "分页查询我的收藏")
    @PostMapping(value = "/getByPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse login(@Validated @RequestBody CollectQry qry) {
        return WrapperResponse.success(collectQueryService.getByPage(qry));
    }

    @ApiOperation(httpMethod = "POST", value = "判断是否收藏")
    @PostMapping(value = "/checkIsCollect", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse checkIsCollect(@Validated @RequestBody CollectCmd cmd) {
        return WrapperResponse.success(collectComService.checkIsCollect(cmd));
    }
}
