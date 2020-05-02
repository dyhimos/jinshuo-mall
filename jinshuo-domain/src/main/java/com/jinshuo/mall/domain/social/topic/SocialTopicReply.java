package com.jinshuo.mall.domain.social.topic;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicReply extends IdentifiedEntity {
    private SocialTopicReplyId socialTopicReplyId;
    /**
     * commentId
     */
    private Long commentId;
    /**
     * 回复类型 0 回复评论 1 回复回复
     */
    private Integer replyType;
    /**
     * replyType为0回复评论replyId为评论ID（commentId） ；replyType为0回复评论 replyId为回复ID（socialTopicReplyId）
     */
    private Long replyId;
    /**
     * 回复用户编号
     */
    private Long userId;
    /**
     * 回复目标用户编号
     */
    private Long toUserId;
    /**
     * 回复时间
     */
    private Date replyTime;
    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;
    /**
     *
     */
    private Long auditUser;
    /**
     *
     */
    private Date auditTime;
    /**
     *
     */
    private String content;

    public void init() {
        super.preInsert();
        this.socialTopicReplyId = new SocialTopicReplyId(CommonSelfIdGenerator.generateId());
        this.auditStatus = 1;
        this.replyTime = new Date();
    }

    public void review(Integer auditStatus) {
        this.auditStatus = auditStatus;
        this.updateDate = new Date();
        this.auditTime = new Date();
        //this.auditUser = UserIdUtils.getUserId();
        //SpringUtil.getBean(SocialTopicReplyRepo.class).review(this);
    }
}