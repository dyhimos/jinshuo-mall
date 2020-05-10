package com.jinshuo.mall.service.social.application.qry;

import lombok.Data;

/**
 * Created by 19458 on 2019/12/26.
 */
@Data
public class SocialTopicQry extends PageQry {

    private Long commectId;

    private Long id;

    private Long shopId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String contents;
    /**
     * 话题属性
     */
    private Integer attr;
    /**
     * 是否置顶
     */
    private Integer isTop;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * userId
     */
    private Long userId;
    /**
     * 圈子编号
     */
    private Long circleId;
    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;
    /**
     * 审核人
     */
    private Long auditUser;

    /**
     * 点赞类型 0点赞帖子 1点赞评论
     */
    private Integer operateType;

    /**
     * 屏蔽userId
     */
    private Long shieldUserId;
}
