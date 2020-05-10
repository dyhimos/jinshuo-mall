package com.jinshuo.mall.service.social.application.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialTopicReplyCmd {
    /**
     * 回复类型 0 回复评论 1 回复回复
     */
    private Integer replyType;
    /**
     *replyType为0回复评论replyId为评论ID（commentId） ；replyType为0回复评论 replyId为回复ID（socialTopicReplyId）
     */
    private Long replyId;
    /**
     *回复内容
     */
    private String content;
}