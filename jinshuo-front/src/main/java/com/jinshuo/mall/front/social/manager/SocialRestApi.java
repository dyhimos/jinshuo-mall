package com.jinshuo.mall.front.social.manager;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.social.application.cmd.SocialCircleCmd;
import com.jinshuo.mall.service.social.application.cmd.SocialRecommendCmd;
import com.jinshuo.mall.service.social.application.cmd.SocialReviewCmd;
import com.jinshuo.mall.service.social.application.cmd.SocialTopicCmd;
import com.jinshuo.mall.service.social.application.dto.SocialTopicCommentDto;
import com.jinshuo.mall.service.social.application.dto.SocialTopicDto;
import com.jinshuo.mall.service.social.application.qry.SocialCommentQry;
import com.jinshuo.mall.service.social.application.qry.SocialTopicQry;
import com.jinshuo.mall.service.social.service.com.SocialCircleComService;
import com.jinshuo.mall.service.social.service.com.SocialTopicComService;
import com.jinshuo.mall.service.social.service.query.SocialTopicQueryService;
import com.jinshuo.mall.service.user.service.query.UserAccountQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 19458 on 2019/9/10.
 */
@RestController
@Api(description = "社区API")
@RequestMapping("/v1/manager/social")
public class SocialRestApi {

    @Autowired
    private SocialCircleComService socialCircleComService;

    @Autowired
    private SocialTopicQueryService socialTopicQueryService;

    @Autowired
    private SocialTopicComService socialTopicComService;

    @Autowired
    private UserAccountQueryService userAccountQueryService;


    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "保存圈子")
    public WrapperResponse recharge(@Valid @RequestBody SocialCircleCmd cmd) {
        socialCircleComService.save(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/quryTopicPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询帖子")
    public WrapperResponse quryTopicPage(@Valid @RequestBody SocialTopicQry qry) {
        PageInfo pageInfo = socialTopicQueryService.quryTopicPage(qry);
        List<SocialTopicDto> dtos = pageInfo.getList();
        for (SocialTopicDto dto : dtos) {
            setTopicUserInfoSingle(dto);
        }
        pageInfo.setList(dtos);
        return WrapperResponse.success(pageInfo);
    }

    @PostMapping("/quryTopicCommentPage")
    @ApiOperation(httpMethod = "POST", value = "后台管理查询评论")
    public WrapperResponse quryTopicCommentPage(@Valid @RequestBody SocialCommentQry qry) {
        PageInfo pageInfo = socialTopicQueryService.quryTopicCommentPageManager(qry);
        List<SocialTopicCommentDto> dtos = pageInfo.getList();
        for (SocialTopicCommentDto dto : dtos) {
            Member member = userAccountQueryService.queryByUserId(dto.getUserId());
            if (null != member && null != member.getSurname()) {
                dto.setUserName(member.getSurname());
            }
        }
        pageInfo.setList(dtos);
        return WrapperResponse.success(pageInfo);
    }

    @PostMapping("/quryTopicReplyPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询回复")
    public WrapperResponse quryTopicReplyPage(@Valid @RequestBody SocialTopicQry qry) {
        return WrapperResponse.success(socialTopicQueryService.quryTopicPage(qry));
    }

    @PostMapping("/reviewTopic")
    @ApiOperation(httpMethod = "POST", value = "审核帖子（评论及回复）")
    public WrapperResponse reviewTopic(@Valid @RequestBody SocialReviewCmd cmd) {
        socialTopicComService.reviewTopic(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/recommendTopic")
    @ApiOperation(httpMethod = "POST", value = "将帖子设置为推荐")
    public WrapperResponse recommendTopic(@Valid @RequestBody SocialRecommendCmd cmd) {
        return WrapperResponse.success(socialTopicComService.recommendTopic(cmd));
    }

    @PostMapping("/getTopic")
    @ApiOperation(httpMethod = "POST", value = "查询帖子详情")
    public WrapperResponse getTopic(@Valid @RequestBody SocialTopicQry qry) {
        SocialTopicDto dto = socialTopicQueryService.quryByTopicId(qry);
        setTopicUserInfoSingle(dto);
        return WrapperResponse.success(dto);
    }

    @PostMapping("/savetopic")
    @ApiOperation(httpMethod = "POST", value = "后台发帖子")
    public WrapperResponse savetopic(@Valid @RequestBody SocialTopicCmd cmd) {
        return WrapperResponse.success(socialTopicComService.saveTopic(cmd, 1));
    }

    public void setTopicUserInfoSingle(SocialTopicDto dto) {
        Member member = userAccountQueryService.queryByUserId(dto.getUserId());
        if (member != null) {
            if (null != member.getNickname()) {
                dto.setUserName(member.getNickname());
            }
            if (null != member.getAvatar()) {
                dto.setAvatar(member.getAvatar());
            }
        }
        if (null != dto.getUserType() && 1 == dto.getUserType()) {
            dto.setUserName("admin");
        }
    }

    @GetMapping("/deleteTopic/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据广告id删除广告")
    public WrapperResponse deleteTopic(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(socialTopicComService.deleteTopic(id));
    }


}
