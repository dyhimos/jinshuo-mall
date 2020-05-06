package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.NoticeCmd;
import com.jinshuo.mall.service.user.application.qry.NoticeQry;
import com.jinshuo.mall.service.user.service.command.NoticeComService;
import com.jinshuo.mall.service.user.service.query.NoticeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/12/11.
 */
@Slf4j
@RestController
@Api(description = "通知")
@RequestMapping("/v1/manager/notice")
public class NoticeRestApi {

    @Autowired
    private NoticeComService noticeComService;

    @Autowired
    private NoticeQueryService noticeQueryService;




    @PostMapping("/getNoticPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询通知公告")
    public WrapperResponse getNoticPage(@RequestBody NoticeQry qry) {
        return WrapperResponse.success(noticeQueryService.getNoticePage(qry));
    }

    @PostMapping("/saveNotice")
    @ApiOperation(httpMethod = "POST", value = "保存通知")
    public WrapperResponse saveUser(@RequestBody NoticeCmd cmd) {
        return WrapperResponse.success(noticeComService.save(cmd));
    }

    @GetMapping("/getNoticeDetail/{id}")
    @ApiOperation(httpMethod = "GET", value = "查询通知公告详情")
    public WrapperResponse saveUser(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(noticeQueryService.getNoticeDetal(id));
    }

    @GetMapping("/deleteNotice/{id}")
    @ApiOperation(httpMethod = "GET", value = "删除通知公告")
    public WrapperResponse deleteNotice(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(noticeComService.delete(id));
    }


}
