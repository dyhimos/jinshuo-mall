package com.jinshuo.mall.front.social.user;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.social.application.cmd.*;
import com.jinshuo.mall.service.social.application.dto.SocialTopicDto;
import com.jinshuo.mall.service.social.application.qry.SocialTopicQry;
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
@RequestMapping("/v1/user/social")
public class WxSocialRestApi {

    @Autowired
    private SocialTopicComService socialTopicComService;

    @Autowired
    private SocialTopicQueryService socialTopicQueryService;

    @Autowired
    private UserAccountQueryService userAccountQueryService;


    @PostMapping("/savetopic")
    @ApiOperation(httpMethod = "POST", value = "保存帖子")
    public WrapperResponse savetopic(@Valid @RequestBody SocialTopicCmd cmd) {
        return WrapperResponse.success(socialTopicComService.saveTopic(cmd, 0));
    }

    @PostMapping("/getTopic")
    @ApiOperation(httpMethod = "POST", value = "查询帖子详情")
    public WrapperResponse getTopic(@Valid @RequestBody SocialTopicQry qry) {
        SocialTopicDto dto = socialTopicQueryService.quryByTopicId(qry);
        setTopicUserInfoSingle(dto);
        return WrapperResponse.success(dto);
    }

    @PostMapping("/commenttopic")
    @ApiOperation(httpMethod = "POST", value = "帖子评论")
    public WrapperResponse commenttopic(@Valid @RequestBody SocialTopicCommentCmd cmd) {
        socialTopicComService.commenttopic(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/replytopic")
    @ApiOperation(httpMethod = "POST", value = "回复评论")
    public WrapperResponse replytopic(@Valid @RequestBody SocialTopicReplyCmd cmd) {
        return WrapperResponse.success(socialTopicComService.replytopic(cmd));
    }

    @PostMapping("/uptopic")
    @ApiOperation(httpMethod = "POST", value = "帖子点赞")
    public WrapperResponse uptopic(@Valid @RequestBody TopicOperateCmd cmd) {
        return WrapperResponse.success(socialTopicComService.uptopic(cmd));
    }

    @PostMapping("/collecttopic")
    @ApiOperation(httpMethod = "POST", value = "收藏")
    public WrapperResponse recharge(@Valid @RequestBody TopicOperateCmd cmd) {
        return WrapperResponse.success(socialTopicComService.collecttopic(cmd));
    }


    @PostMapping("/quryTopicPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询帖子")
    public WrapperResponse quryTopicPage(@RequestBody SocialTopicQry qry) {
        qry.setAuditStatus(0);
        PageInfo pageInfo = socialTopicQueryService.frontQuryTopicPage(qry);
        setTopicUserInfo(pageInfo);
        return WrapperResponse.success(pageInfo);
    }

    @PostMapping("/quryMyTopicPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询我的帖子")
    public WrapperResponse quryMyTopicPage(@RequestBody SocialTopicQry qry) {
        qry.setUserId(UserIdUtils.getUserId());
        PageInfo pageInfo = socialTopicQueryService.frontQuryTopicPage(qry);
        setTopicUserInfo(pageInfo);
        return WrapperResponse.success(pageInfo);
    }

    @PostMapping("/queryMyCollecttopic")
    @ApiOperation(httpMethod = "POST", value = "分页查询我收藏的帖子")
    public WrapperResponse queryMyCollecttopic(@RequestBody SocialTopicQry qry) {
        PageInfo pageInfo = socialTopicQueryService.queryMyCollecttopic(qry);
        setTopicUserInfo(pageInfo);
        return WrapperResponse.success(pageInfo);
    }

    @PostMapping("/quryTopicCommentPage")
    @ApiOperation(httpMethod = "POST", value = "客户分页查询评论")
    public WrapperResponse quryTopicCommentPage(@RequestBody SocialTopicQry qry) {
        return WrapperResponse.success(socialTopicQueryService.frontQuryTopicCommentPage(qry));
    }

    @GetMapping("/deleteComment/{id}")
    @ApiOperation(httpMethod = "GET", value = "删除我的评论")
    public WrapperResponse deleteComment(@PathVariable(value = "id") Long id) {
        return WrapperResponse.success(socialTopicComService.deleteComment(id));
    }

    @PostMapping("/quryMyCommentPage")
    @ApiOperation(httpMethod = "POST", value = "客户分页查询我的评论")
    public WrapperResponse quryMyCommentPage(@Valid @RequestBody SocialTopicQry qry) {
        return WrapperResponse.success(socialTopicQueryService.quryMyCommentPage(qry));
    }

    @PostMapping("/quryMyUpPage")
    @ApiOperation(httpMethod = "POST", value = "客户分页查询我的点赞")
    public WrapperResponse quryMyUpPage(@RequestBody SocialTopicQry qry) {
        return WrapperResponse.success(socialTopicQueryService.quryMyUpPage(qry));
    }

    @PostMapping("/quryMyUpedPage")
    @ApiOperation(httpMethod = "POST", value = "查询我被点赞的帖子评论")
    public WrapperResponse quryMyUpedPage(@RequestBody SocialTopicQry qry) {
        return WrapperResponse.success(socialTopicQueryService.quryMyUpedPage(qry));
    }

    @PostMapping("/shieldTopic")
    @ApiOperation(httpMethod = "POST", value = "屏蔽帖子或人")
    public WrapperResponse shieldTopic(@RequestBody SocialTopicShieldCmd cmd) {
        return WrapperResponse.success(socialTopicComService.shieldTopic(cmd));
    }

    @PostMapping("/cancelShield/{id}")
    @ApiOperation(httpMethod = "GET", value = "取消屏蔽")
    public WrapperResponse shieldTopic(Long id) {
        return WrapperResponse.success(socialTopicComService.cancelShield(id));
    }


    public void setTopicUserInfo(PageInfo pageInfo) {
        List<SocialTopicDto> dtos = pageInfo.getList();
        for (SocialTopicDto dto : dtos) {
            setTopicUserInfoSingle(dto);
        }
        pageInfo.setList(dtos);
    }

    public void setTopicUserInfoSingle(SocialTopicDto dto) {
        Member member = userAccountQueryService.queryByUserId(dto.getUserId());
        if (null != member) {
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

}
