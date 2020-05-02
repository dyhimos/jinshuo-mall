package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/7/30.
 */
@Data
public class CouponUpdateCmd {

    @NotNull(message = "ID不能为空！")
    @ApiModelProperty(value = "ID")
    private Long id;

    @NotNull(message = "优惠券名称不能为空！")
    @ApiModelProperty(value = "优惠券名称")
    private String name;             //优惠券名称

    @NotNull(message = "优惠券类型不能为空！")
    @ApiModelProperty(value = "优惠券类型")
    private Integer type;             //优惠券类型

    @NotNull(message = "面值不能为空！")
    @ApiModelProperty(value = "面值")
    private BigDecimal couponAmount;//面值

    //@NotNull(message = "减免金额不能为空！")
    @ApiModelProperty(value = "减免金额")
    private BigDecimal amount;           //减免金额

    @NotNull(message = "发行数量不能为空！")
    @ApiModelProperty(value = "发行数量")
    private Integer maxLimit;        //发行数量

    @NotNull(message = "可领取开始时间不能为空！")
    @ApiModelProperty(value = "可领取开始时间")
    private Date receiveStartTime;   //可领取开始时间

    @NotNull(message = "可领取结束时间不能为空！")
    @ApiModelProperty(value = "可领取结束时间")
    private Date receiveEndTime;    //可领取结束时间

    @NotNull(message = "门槛规则不能为空！")
    @ApiModelProperty(value = "门槛规则")
    private Integer isCondition;     //门槛规则

    @NotNull(message = "适用范围类型不能为空！")
    @ApiModelProperty(value = "适用范围类型")
    private Integer scopeType;       //适用范围类型

    @NotNull(message = "有效期类型不能为空！")
    @ApiModelProperty(value = "有效期类型")
    private Integer validitType;     //有效期类型

    //@NotNull(message = "有效期开始时间不能为空！")
    @ApiModelProperty(value = "有效期开始时间")
    private Date validityStartDate; //绝对有效期开始时间

    //@NotNull(message = "有效期结束时间不能为空！")
    @ApiModelProperty(value = "有效期结束时间")
    private Date vaildityEndDate;   //绝对有效期-结束时间

    //@NotNull(message = "相对有效期天数不能为空！")
    @ApiModelProperty(value = "相对有效期天数")
    private Integer vaildityDays;    //相对有效期天数

    @NotNull(message = "限领张数不能为空！")
    @ApiModelProperty(value = "限领张数")
    private Integer gainCount;//限领张数

    @NotNull(message = "用户获取方式不能为空！")
    @ApiModelProperty(value = "用户获取方式")
    private Integer gainMethod;//用户获取方式

    @NotNull(message = "优惠说明不能为空！")
    @ApiModelProperty(value = "优惠说明")
    private String couponDesc;//优惠说明

    @NotNull(message = "排序不能为空！")
    @ApiModelProperty(value = "排序")
    private Integer sort;//排序

    //@NotNull(message = "是否生效不能为空！")
    @ApiModelProperty(value = "是否生效")
    private Integer couponStatus;           //是否生效

    //@NotNull(message = "id不能为空！")
    @ApiModelProperty(value = "审核状态")
    private Integer autStatus;      //审核状态

    @NotNull(message = "可用产品信息不能为空！")
    @ApiModelProperty(value = "可用产品信息")
    private List<CouponItemCreateCmd> items;

    private Integer isShow = 0;//是否展示 0展示 1不展示
    private Long shopId;//店铺ID
    private Integer forPeople = 0;//适用人群 0:所有人 1:成为会员但不是粉丝
}
