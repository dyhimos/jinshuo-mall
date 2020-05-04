package com.jinshuo.mall.service.wx.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回复文本消息
 * @author dongyh
 * @Title: ReplyTextMessage
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/18 10:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyTextMessage {
    /**
     * 开发者微信号
     */
    private String ToUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;

    /**
     * 消息类型，文本为text
     */
    private String MsgType;

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String Content;
}
