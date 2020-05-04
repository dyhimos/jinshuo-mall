package com.jinshuo.mall.service.wx.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信接收事件
 * @author dongyh
 * @Title: WxReceiveMessageDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/18 9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxReceiveMessageDto {
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
     * 消息类型，event
     */
    private String MsgType;

    /**
     * 接收普通消息文本内容
     */
    private String Content;

    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     *  LOCATION（上报地理位置事件）
     *  CLICK 自定义菜单事件 点击菜单拉取消息时的事件推送
     *  VIEW 自定义菜单事件 点击菜单跳转链接时的事件推送
     *  SCAN 扫描带参数二维码事件(已关注时候触发)
     */
    private String Event;

    //扫描带参数二维码事件（添加一下两个参数）
    /**
     * 事件KEY值
     */
    private String EventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;


    //上报地理位置事件添加下面三个参数
    /**
     * 地理位置纬度
     */
    private Double Latitude;

    /**
     * 地理位置经度
     */
    private Double Longitude;


    /**
     * 地理位置精度
     */
    private Double Precision;
}
