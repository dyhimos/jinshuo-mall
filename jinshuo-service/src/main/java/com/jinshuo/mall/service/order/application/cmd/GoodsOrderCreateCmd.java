package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Classname GoodsOrderCreateCmd
 * @Description 订单查询
 * @Date 2019/6/27 15:36
 * @Created by dongyh
 * @author dongyh
 */

@Data
@Accessors(chain = true)
public class GoodsOrderCreateCmd {

    @ApiModelProperty(value = "1、实物商品；2、串号产品（虚拟）；3、电子卡券（虚拟）")
    @NotNull(message = "订单类型")
    private Integer orderType;

    @ApiModelProperty(value = "商户编号")
    private Long supplierId;

    @ApiModelProperty(value = "商品数")
    @NotNull(message = "商品数")
    @Range(min = 1,max = 999,message="商品数量只能是1-999之间")
    private Integer goodsCount;

    @ApiModelProperty(value = "收货地址")
    private GoodsOrderAddressCmd goodsOrderAddressCmd;

    @ApiModelProperty(value = "订单产品信息")
    private List<GoodsOrderDetailCmd> goodsOrderDetailCmdList;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "优惠券领取ID")
    private String couponReceiveId;
}
