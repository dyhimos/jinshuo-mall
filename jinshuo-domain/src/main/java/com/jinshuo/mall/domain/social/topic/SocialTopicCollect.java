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
public class SocialTopicCollect extends IdentifiedEntity {
    private SocialTopicCollectId socialTopicCollectId;

    private Long topicId;

    private Long userId;

    private Date collectTime;

    public void init() {
        super.preInsert();
        this.socialTopicCollectId = new SocialTopicCollectId(CommonSelfIdGenerator.generateId());
    }
}