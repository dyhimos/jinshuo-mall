package com.jinshuo.mall.service.social.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialCircleCmd {
    private String name;

    private String remark;

    private Integer weight;

    private Integer topicCount;

    private Integer userCount;
}