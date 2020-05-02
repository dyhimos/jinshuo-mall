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
public class SocialTopicAlbum extends IdentifiedEntity {
    private SocialTopicAlbumId socialTopicAlbumId;

    private Long topicId;

    private String photoUrl;

    private Integer sort;

    private Date addTime;

    public void init() {
        super.preInsert();
        this.socialTopicAlbumId = new SocialTopicAlbumId(CommonSelfIdGenerator.generateId());
        this.addTime = new Date();
    }

}