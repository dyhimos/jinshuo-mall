package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/7/30.
 */
@Data
public class CouponQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "是否生效")
    private Integer couponStatus;//是否生效 1、未开始；2、进行中；3、已结束

    @ApiModelProperty(value = "目标ID 如spuId")
    private Long targetId;//目标id

    @ApiModelProperty(value = "优惠券id")
    private List<Long> couponIds;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;//
}
