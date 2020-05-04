package com.jinshuo.mall.service.wx.application.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 回复图片消息
 * @author dongyh
 * @Title: ReplyTextMessage
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/18 10:22
 */
@Data
@Builder
public class ImageDto {
    /**
     * 开发者微
     */
    private String MediaId;
}
