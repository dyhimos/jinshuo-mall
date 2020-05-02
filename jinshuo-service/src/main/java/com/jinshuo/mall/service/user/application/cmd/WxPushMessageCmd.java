package com.jinshuo.mall.service.user.application.cmd;

import lombok.Builder;
import lombok.Data;

/**
 * 微信推送信息发送接口
 * @author 
 * @version 2016-04-17
 */
@Data
@Builder
public class WxPushMessageCmd {


	/**
	 * 接收方帐号（收到的OpenID）
	 */
	private String ToUserName;

	/**
	 * 开发者微信号
	 */
	private String FromUserName;

	/**
	 * 消息创建时间 （整型）
	 */
	private Long CreateTime;

	/**
	 * 消息类型 （文本：text 图片：image）
	 */
	private String MsgType;

	/**
	 * 图片链接
	 */
	//private String PicUrl;

	private ImageCmd Image;

	/**
	 * 图片消息媒体id
	 */
	//private Long MsgId;
}