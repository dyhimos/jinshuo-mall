package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Classname SpuQry
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dongyh
 */
@Data
public class GoodsOrderQry {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "会员昵称")
    private String memberName;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "收货人名称")
    private String userName;

    @ApiModelProperty(value = "收件人手机号码")
    private String userPhone;

    @ApiModelProperty(value = "创建查询时间开始")
    private Date createStarTime;

    @ApiModelProperty(value = "创建查询时间结束")
    private Date createEndTime;

    @ApiModelProperty(value = "支付查询时间开始")
    private Date payStarTime;

    @ApiModelProperty(value = "支付查询时间结束")
    private Date payEndTime;

    @ApiModelProperty(value = "订单完成查询时间开始")
    private Date finshStarTime;

    @ApiModelProperty(value = "订单完成创建查询时间结束")
    private Date finshEndTime;
}
