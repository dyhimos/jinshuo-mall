package com.jinshuo.mall.service.order.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Classname GoodsOrderDto
 * @Description 返回寄样订单列表信息
 * @Date 2019/6/28 9:29
 * @Created by dongyh
 * @author  dongyh
 */
@Data
public class GoodsSimpleOrderListDto {

    @ApiModelProperty(value = "订单id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "寄样信息")
    private String sampleInfo;

    @ApiModelProperty(value = "收货人名称")
    private String userName;

    @ApiModelProperty(value = "收件人地址")
    private String userAddress;

    @ApiModelProperty(value = "收件人手机号码")
    private String userPhone;

    @ApiModelProperty(value = "寄样订单状态 1：待发货  2：待收货")
    private Integer sampleStatus;

    @ApiModelProperty(value = "寄样单号")
    private String sampleNo;

    @ApiModelProperty(value = "快递公司")
    private String expressCompany;

    @ApiModelProperty(value = "快递单号")
    private String expressNo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "发货时间")
    private Date expressDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "下单时间")
    private Date createDate;
}
