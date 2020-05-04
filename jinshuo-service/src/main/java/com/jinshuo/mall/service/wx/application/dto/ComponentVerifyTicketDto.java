package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 微信推送到第三方平台的ComponentVerifyTicket
 * @author dongyh
 * @Title: ComponentVerifyTicketDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class ComponentVerifyTicketDto {
    /**
     * 第三方平台 appid
     */
    private String AppId;

    /**
     * 时间戳，单位：s
     */
    private Long CreateTime;

    /**
     * 固定为："component_verify_ticket"
     */
    private String InfoType;

    /**
     * Ticket 内容
     */
    private String ComponentVerifyTicket;
}
