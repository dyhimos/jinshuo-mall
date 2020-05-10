package com.jinshuo.mall.service.social.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicShieldCmd {
    /**
     * 0屏蔽的是帖子 1屏蔽的是人
     */
    @ApiModelProperty(value = "0屏蔽的是帖子 1屏蔽的是人")
    private Integer shieldType;
    /**
     * 帖子id或userId
     */
    @ApiModelProperty(value = "目标id")
    private Long targetId;
}