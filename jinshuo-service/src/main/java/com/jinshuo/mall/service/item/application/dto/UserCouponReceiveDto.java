package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
public class UserCouponReceiveDto {

    private String id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long couponId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long memId;
    private String couponCode;
    private Date receiveTime;
    private Date useTime;
    private Integer useStatus; // 状态，1为已使用，0为已领取未使用，-1为已过期
    private String name;             //优惠券名称
    private Integer type;             //优惠券类型 1现金券 2满减券 3折扣券
    private BigDecimal couponAmount;//面值
    private BigDecimal amount;           //减免金额
    private Integer isCondition;     //门槛规则 1、满减；2、无门槛
    private Integer scopeType;       //适用范围类型 1、全部商品可用；2、指定商品可用；3、指定商品不可用
    private Integer validitType;     //有效期类型  1、表示指定时间；2、表示固定时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date validityStartDate; //绝对有效期开始时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date vaildityEndDate;   //绝对有效期-结束时间
    private Integer vaildityDays;    //相对有效期天数
    private String couponDesc;//优惠说明
    private Integer couponStatus;           //是否生效 1、未开始；2、进行中；3、已结束
}
