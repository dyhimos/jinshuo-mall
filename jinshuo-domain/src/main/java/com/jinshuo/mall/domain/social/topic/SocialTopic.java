package com.jinshuo.mall.domain.social.topic;

import com.jinshuo.core.exception.sc.ScBizException;
import com.jinshuo.core.exception.sc.ScReturnCode;
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
public class SocialTopic extends IdentifiedEntity {
    private SocialTopicId socialTopicId;

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
     * 用户类型 0->客户 1->后台发帖
     */
    private Integer userType;
    /**
     * userId
     */
    private Long userId;
    /**
     * 圈子编号
     */
    private Long circleId;
    /**
     * 回复数
     */
    private Integer replyCount;
    /**
     * 点击数
     */
    private Integer clickCount;
    /**
     * 点赞数
     */
    private Integer upCount;
    /**
     * 收藏数
     */
    private Integer collectCount;
    /**
     * 发帖时间
     */
    private Date topicTime;
    /**
     * 最后修改时间
     */
    private Date updateTime;
    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;
    /**
     * 审核人
     */
    private Long auditUser;
    /**
     * 审核时间
     */
    private Date auditTime;

    public void init() {
        super.preInsert();
        this.upCount = 0;
        this.collectCount = 0;
        this.replyCount = 0;
        this.topicTime = new Date();
        this.auditStatus = 1;
        this.socialTopicId = new SocialTopicId(CommonSelfIdGenerator.generateId());
    }

    public void update(String title, String contents) {
        if (0 == this.auditStatus) {
            throw new ScBizException(ScReturnCode.IC209002.getCode(), ScReturnCode.IC209002.getMsg());
        }
        this.title = title;
        this.contents = contents;
        this.updateTime = new Date();
        this.updateDate = new Date();
    }

    public void up() {
        if (null == this.upCount) {
            this.upCount = 0;
        }
        this.upCount = this.upCount + 1;
        this.updateDate = new Date();
        // SpringUtil.getBean(SocialTopicRepo.class).up(this);
    }

    public void down() {
        if (null == this.upCount || this.upCount < 1) {
            return;
        }
        this.upCount = this.upCount - 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicRepo.class).up(this);
    }

    public void collect() {
        if (null == this.collectCount) {
            this.collectCount = 0;
        }
        this.collectCount = this.collectCount + 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicRepo.class).collect(this);
    }

    public void cacelCollect() {
        if (null == this.collectCount || this.collectCount < 1) {
            return;
        }
        this.collectCount = this.collectCount - 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicRepo.class).collect(this);
    }

    public void reply() {
        if (null == this.replyCount) {
            this.replyCount = 0;
        }
        this.replyCount = this.replyCount + 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicRepo.class).reply(this);
    }

    public void review(Integer auditStatus) {
        this.auditStatus = auditStatus;
        this.updateDate = new Date();
        this.auditTime = new Date();
        //this.auditUser = UserIdUtils.getUserId();
        //SpringUtil.getBean(SocialTopicRepo.class).review(this);
    }

    public void unReply() {
        if (null == this.replyCount || this.replyCount < 1) {
            return;
        }
        this.replyCount = this.replyCount - 1;
        this.updateDate = new Date();
        //SpringUtil.getBean(SocialTopicRepo.class).reply(this);
    }

}