package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
public class CouponReceiveQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "会员编码")
    private Long memId;

    @ApiModelProperty(value = "状态，1为已使用，0为已领取未使用，-1为已过期")
    private Integer useStatus; // 状态，1为已使用，0为已领取未使用，-1为已过期

    @ApiModelProperty(value="产品id")
    private Long targetId;

    @ApiModelProperty(value="数量")
    private Integer count;

    @ApiModelProperty(value="满减金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "优惠券id")
    private List<Long> couponIds;
}
