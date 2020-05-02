package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.TopicAddProductCmd;
import com.jinshuo.mall.service.item.application.cmd.TopicCmd;
import com.jinshuo.mall.service.item.application.qry.TopicProductPageQry;
import com.jinshuo.mall.service.item.application.qry.TopicProductQry;
import com.jinshuo.mall.service.item.application.qry.TopicQry;
import com.jinshuo.mall.service.item.service.command.TopicComService;
import com.jinshuo.mall.service.item.service.command.TopicProductComService;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import com.jinshuo.mall.service.item.service.query.TopicQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/10.
 */
@RestController
@Api(description = "活动管理接口")
@RequestMapping("/v1/manager/topic")
public class TopicRestApi {
    @Autowired
    private TopicComService topicComService;

    @Autowired
    private TopicQueryService topicQueryService;

    @Autowired
    private TopicProductComService topicProductComService;

    @Autowired
    private SpuQueryService spuQueryService;


    @PostMapping("/saveTopic")
    @ApiOperation(httpMethod = "POST", value = "保存活动（主题）")
    public WrapperResponse createAdPosition(@Valid @RequestBody TopicCmd cmd) {
        topicComService.create(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(httpMethod = "GET", value = "删除活动（主题）")
    public WrapperResponse getTopics(@PathVariable(value = "id") Long id) {
        topicComService.delete(id);
        return WrapperResponse.success();
    }

    @GetMapping("/getTopicInfo/{id}")
    @ApiOperation(httpMethod = "GET", value = "查询活动详情")
    public WrapperResponse getTopicInfo(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success( topicQueryService.getTopic(id));
    }

    @GetMapping("/getTopics/{type}")
    @ApiOperation(httpMethod = "GET", value = "获取活动（主题）集合")
    public WrapperResponse getTopics(@PathVariable(value = "type") Integer type) {
        return WrapperResponse.success(topicQueryService.getTopics(type));
    }

    @PostMapping("/getTopicPage")
    @ApiOperation(httpMethod = "POST", value = "获取活动（主题）分页")
    public WrapperResponse getTopicPage(@RequestBody TopicQry qry) {
        return WrapperResponse.success(topicQueryService.getTopicPage(qry));
    }

    @GetMapping("/getTheme/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据活动id查询主题列表")
    public WrapperResponse getTheme(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(topicQueryService.getTheme(id));
    }

    @PostMapping("/addProduct")
    @ApiOperation(httpMethod = "POST", value = "添加产品")
    public WrapperResponse addProduct(@Valid @RequestBody TopicAddProductCmd cmd) {
        topicProductComService.create(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/deleteProduct/{id}")
    @ApiOperation(httpMethod = "GET", value = "将产品从活动中移除（主题）")
    public WrapperResponse deleteProduct(@PathVariable(value = "id") Long id) {
        topicProductComService.delete(id);
        return WrapperResponse.success();
    }

    @GetMapping("/updateProductSort/{id}/{sort}")
    @ApiOperation(httpMethod = "GET", value = "修改产品主题中的排序")
    public WrapperResponse updateProductSort(@PathVariable(value = "id") Long id, @PathVariable(value = "sort") Integer sort) {
        topicProductComService.updateProductSort(id, sort);
        return WrapperResponse.success();
    }


    @PostMapping(value = "/getTopicProduct", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "查询已添加到主题的产品信息（主题添加产品使用）")
    public WrapperResponse getTopicProduct(@RequestBody TopicProductQry qry) {
        return WrapperResponse.success(spuQueryService.getTopicProduct(qry));
    }

    @PostMapping(value = "/getNotTopicProduct", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(httpMethod = "POST", value = "分页查询未添加到主题的产品信息（主题添加产品使用）")
    public WrapperResponse getNotYetTopicProduct(@RequestBody TopicProductPageQry qry) {
        return WrapperResponse.success(spuQueryService.getNotYetTopicProduct(qry));
    }


}
