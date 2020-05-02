package com.jinshuo.mall.domain.social.social;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialCircle extends IdentifiedEntity {
    private SocialCircleId socialCircleId;

    private Long shopId;

    private String name;

    private String remark;

    private Integer weight;

    private Integer topicCount;

    private Integer userCount;

    public void init() {
        super.preInsert();
        this.socialCircleId = new SocialCircleId(CommonSelfIdGenerator.generateId());
    }
}