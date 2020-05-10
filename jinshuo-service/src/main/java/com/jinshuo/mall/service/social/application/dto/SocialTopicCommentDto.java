package com.jinshuo.mall.service.social.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author dyh
 */
@Data
public class SocialTopicCommentDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentTime;

    private String content;

    private Integer isUp;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String userName;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String contents;
    /**
     * 审核状态 1 待审核 0已审核 2审核不通过
     */
    private Integer auditStatus;
}