package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/7/30.
 */
@Data
public class CouponItemDto {
    private String id;

    @ApiModelProperty(value = "优惠券编码")
    private String couponId;//优惠券编码

    @ApiModelProperty(value = "目标对象")
    private String targetId;//目标对象

    @ApiModelProperty(value = "类型")
    private Integer type;//类型

    @ApiModelProperty(value = "是否适用类型")
    private Integer isApply;//是否适用类型

    @ApiModelProperty(value = "产品图片")
    private String pictureUrl;//产品图片

    @ApiModelProperty(value = "名称")
    private String name;//名称

    @ApiModelProperty(value = "价格")
    private BigDecimal price;//价格

    @ApiModelProperty(value = "库存")
    private Integer stock;//库存

    public CouponItemDto setProduct(String pictureUrl, String productName, BigDecimal productPrice, Integer productStock) {
        if (StringUtils.isNotBlank(pictureUrl)) {
            this.pictureUrl = pictureUrl;
        }
        if (StringUtils.isNotBlank(productName)) {
            this.name = productName;
        }
        if (null != productPrice) {
            this.price = productPrice;
        }
        if (null != productStock) {
            this.stock = productStock;
        }
        return this;
    }
}
