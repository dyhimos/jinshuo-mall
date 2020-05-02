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
public class ImageCmd {

	/**
	 * 图片消息媒体id
	 */
	private String MediaId;
}