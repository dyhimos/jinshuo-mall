package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.AlbumCreateCmd;
import com.jinshuo.mall.service.item.application.qry.AlbumQry;
import com.jinshuo.mall.service.item.service.command.AlbumComService;
import com.jinshuo.mall.service.item.service.query.AlbumQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/7/19.
 */
@RestController
@Api(description = "商品专辑表管理接口")
@RequestMapping("/v1/manager/album")
public class AlbumRestApi {

    @Autowired
    private AlbumQueryService albumQueryService;

    @Autowired
    private AlbumComService albumComService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "创建产品媒体")
    public WrapperResponse create(@Valid @RequestBody AlbumCreateCmd cmd) {
        albumComService.create(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/get")
    @ApiOperation(httpMethod = "POST", value = "获取产品媒体")
    public WrapperResponse get(@Valid @RequestBody AlbumQry qry) {
        return WrapperResponse.success(albumQueryService.getAlbumsByExample(qry));
    }

}
