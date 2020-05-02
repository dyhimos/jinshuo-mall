package com.jinshuo.mall.service.user.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/9/2.
 */
@Data
public class UserMemberDto {
    /**
     * 会员信息ID
     */
    private String id;

    /**
     * 客户号
     */
    private String memNo;
    /**
     * 客户昵称
     */
    private String nickname;

    /**
     * 客户性别
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否分销会员 （1：是  2：不是）
     */
    private Integer isDis;


    /**
     * 消费总额
     */
    private BigDecimal consumeAmount;

    /**
     * 共享分红金额
     */
    private BigDecimal disAmount;

    /**
     * 消费订单数
     */
    private Integer consumeOrder;

    /**
     * 最近消费时间
     */
    private Date consumeTime;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 结算类型 支付渠道1、微信支付 3、支付宝账号
     */
    private Integer payChannel;

    /**
     * 支付宝账号
     */
    private String payNo;

    /**
     * 微信账号
     */
    private String wxNo;

    /**
     * 真实名字
     */
    private String surname;
    /**
     * 手机号码
     */
    private String phone;



    public UserMemberDto changeDisAmount(Integer consumeOrder, BigDecimal consumeAmount, BigDecimal disAmount) {
        if (null != consumeOrder) {
            this.consumeOrder = consumeOrder;
        }
        if (null != consumeAmount) {
            this.consumeAmount = consumeAmount;
        }
        if (null != disAmount) {
            this.disAmount = disAmount;
        }
        return this;
    }

}
