package com.jinshuo.mall.front.item.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.qry.ThemeQry;
import com.jinshuo.mall.service.item.application.qry.TopicProductQry;
import com.jinshuo.mall.service.item.service.query.AdPositionQueryService;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.item.service.query.TopicQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 19458 on 2019/9/23.
 */
@RestController
@Api(description = "活动管理接口")
@RequestMapping("/v1/wx/topic")
public class TopicWxRestApi {

    @Autowired
    private AdPositionQueryService adPositionQueryService;

    @Autowired
    private TopicQueryService topicQueryService;

    @Autowired
    private SpuQueryService spuQueryService;


    @PostMapping("/getThemeByCode")
    @ApiOperation(httpMethod = "POST", value = "根据活动code查询主题列表")
    public WrapperResponse getTheme(@RequestBody ThemeQry qry) {
        return WrapperResponse.success(topicQueryService.getThemeByCode(qry));
    }

    @PostMapping("/getProductByTopic")
    @ApiOperation(httpMethod = "POST", value = "根据主题查询产品")
    public WrapperResponse getProductByTopic(@RequestBody TopicProductQry qry) {
        return WrapperResponse.success(spuQueryService.getTopicProductByFront(qry));
    }


}
