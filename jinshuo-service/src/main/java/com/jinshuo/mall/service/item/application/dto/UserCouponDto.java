package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/8/2.
 */
@Data
public class UserCouponDto {
    private String id;                  //
    private String name;             //优惠券名称
    private Integer type;             //优惠券类型
    private BigDecimal couponAmount;//面值
    private BigDecimal amount;//面值
    private Integer maxLimit;        //发行数量
    private Integer receivedQuantity;//已领取数量
    private Integer isCondition;     //门槛规则
    private Integer scopeType;       //适用范围类型
    private Integer validitType;     //有效期类型

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date validityStartDate; //绝对有效期开始时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date vaildityEndDate;   //绝对有效期-结束时间
    private Integer vaildityDays;    //相对有效期天数
    private Integer gainCount;//限领张数
    private Integer gainMethod;//用户获取方式
    private String couponDesc;//优惠说明
    private Integer sort;//优惠说明
    private Integer couponStatus;           //是否生效
    private Integer auditStatus;      //审核状态
    private Integer receivedStatus;// 领取状态 1：未领取 0：已领取

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date receiveStartTime;   //可领取开始时间-结束时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date receiveEndTime;    //可领取结束时间
    private List<CouponItemDto> items;

    public UserCouponDto changeItems(List<CouponItemDto> items) {
        if (null != items) {
            this.items = items;
        }
        return this;
    }

    public UserCouponDto changeReceivedStatus() {

        return this;
    }
}
