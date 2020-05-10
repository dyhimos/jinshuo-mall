package com.jinshuo.mall.service.social.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/13.
 */
@Data
public class SocialTopicUpDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 点赞类型 0点赞帖子 1点赞评论
     */
    private Integer operateType;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;

    private String title;

    private String contents;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private Date upTime;

    private Integer isUp;

    private Integer upCount;
}
