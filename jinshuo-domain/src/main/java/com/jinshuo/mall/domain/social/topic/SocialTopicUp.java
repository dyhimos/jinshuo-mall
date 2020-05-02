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
public class SocialTopicUp extends IdentifiedEntity {
    private SocialTopicUpId socialTopicUpId;

    /**
     * 点赞类型 0点赞帖子 1点赞评论
     */
    private Integer operateType;

    /**
     * 帖子id 或者 评论id
     */
    private Long topicId;

    private Long userId;

    private Date upTime;

    public void init() {
        super.preInsert();
        this.upTime = new Date();
        this.socialTopicUpId = new SocialTopicUpId(CommonSelfIdGenerator.generateId());
    }

}