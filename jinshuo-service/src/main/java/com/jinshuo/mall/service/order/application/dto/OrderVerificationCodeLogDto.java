package com.jinshuo.mall.service.order.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dongyh
 * @Classname
 * @Description
 * @Date
 * @Created
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVerificationCodeLogDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long verificationId;
    /**
     * 供应商id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long supplierId;
    /**
     * 订单编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodsOrderId;
    /**
     * 订单详情id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodsOrderDetailId;
    /**
     * 验证码
     */
    private String verifySn;
    /**
     * 核销员id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userAccountId;
    /**
     * 核销员名称
     */
    private String name;
    /**
     * 核销状态 0 成功  1失败
     */
    private Integer errCode;
    /**
     * 失败原因
     */
    private String errMsg;

    /**
     * 产品图片
     */
    private String url;

    /**
     * 产品id
     */
    private Long spuId;

    /**
     * 产品名称
     */
    private String spuName;

    /**
     * 产品价格
     */
    private BigDecimal costPrice;

    /**
     * 核销时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    /**
     * 购买人姓名
     */
    private String memberName;
}
