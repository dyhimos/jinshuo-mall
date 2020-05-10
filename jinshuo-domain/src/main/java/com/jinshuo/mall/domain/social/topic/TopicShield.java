package com.jinshuo.mall.domain.social.topic;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/5/4.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicShield extends IdentifiedEntity {
    /**
     * userId
     */
    private Long userId;
    /**
     * 0屏蔽的是帖子 1屏蔽的是人
     */
    private Integer shieldType;
    /**
     * 帖子id或userId
     */
    private Long targetId;

    public void init(){
        super.setId(CommonSelfIdGenerator.generateId());
        super.preInsert();
    }
}
