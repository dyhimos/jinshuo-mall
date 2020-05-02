package com.jinshuo.mall.domain.social.topic;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicComment extends IdentifiedEntity {
    private SocialTopicCommentId socialTopicCommentId;

    private Long topicId;

    private Long userId;

    private Date commentTime;
    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;

    private Long auditUser;

    private Date auditTime;

    private String content;

    /**
     * 点赞数
     */
    private Integer upCount;

    public void init() {
        super.preInsert();
        this.auditStatus = 1;
        this.upCount = 0;
        this.commentTime = new Date();
        this.socialTopicCommentId = new SocialTopicCommentId(CommonSelfIdGenerator.generateId());
    }

    public void up() {
        if (null == this.upCount) {
            this.upCount = 0;
        }
        this.upCount = this.upCount + 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicCommentRepo.class).up(this);
    }

    public void down() {
        if (null == this.upCount || this.upCount < 1) {
            return;
        }
        this.upCount = this.upCount - 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicCommentRepo.class).up(this);
    }

    public void review(Integer auditStatus) {
        this.auditStatus = auditStatus;
        this.updateDate = new Date();
        this.auditTime = new Date();
        //this.auditUser = UserIdUtils.getUserId();
        //SpringUtil.getBean(SocialTopicCommentRepo.class).review(this);
    }
}