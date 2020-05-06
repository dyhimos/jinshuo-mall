package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.qry.NoticeQry;
import com.jinshuo.mall.service.user.service.query.NoticeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2020/1/3.
 */
@Slf4j
@RestController
@Api(description = "通知与培训")
@RequestMapping("/v1/user/notice")
public class WxNoticeRestApi {

    @Autowired
    private NoticeQueryService noticeQueryService;

    /*@PostMapping("/getShowNotice")
    @ApiOperation(httpMethod = "POST", value = "查询首页通知")
    public WrapperResponse getShowNotice(@RequestBody NoticeQry qry) {
        return WrapperResponse.success(noticeQueryService.getShowNotice(qry));
    }*/

    @PostMapping("/getNotice")
    @ApiOperation(httpMethod = "POST", value = "查询通知")
    public WrapperResponse getNotice(@RequestBody NoticeQry qry) {
        return WrapperResponse.success(noticeQueryService.getMyNotice(qry));
    }


    @GetMapping("/getNoticeDetail/{id}")
    @ApiOperation(httpMethod = "GET", value = "查询通知公告详情")
    public WrapperResponse getNoticeDetail(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(noticeQueryService.getNoticeDetal(id));
    }


}
