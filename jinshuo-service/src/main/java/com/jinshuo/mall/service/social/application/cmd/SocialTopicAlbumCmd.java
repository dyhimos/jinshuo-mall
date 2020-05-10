package com.jinshuo.mall.service.social.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicAlbumCmd {

    private Long topicId;

    private String photoUrl;

    private Integer sort;
}