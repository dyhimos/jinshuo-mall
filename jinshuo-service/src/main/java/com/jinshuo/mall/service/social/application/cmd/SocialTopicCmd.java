package com.jinshuo.mall.service.social.application.cmd;

import lombok.Data;

import java.util.List;

@Data
public class SocialTopicCmd {
    private Long id;

    private String title;

    private Integer attr;

    private Long circleId;

    private Integer collectCount;

    private String contents;

    private List<String> urls;
}