package com.jinshuo.mall.service.wx.application.dto;

/**
 * 回复语音消息
 * @author dongyh
 * @Title: ReplyTextVoiceMessage
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/18 10:22
 */
public class ReplyTextVoiceMessage {
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
     * 消息类型，图片为image
     */
    private String MsgType;

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    private Voice voice;

    public class Voice{
        private String MediaId;
    }
}
