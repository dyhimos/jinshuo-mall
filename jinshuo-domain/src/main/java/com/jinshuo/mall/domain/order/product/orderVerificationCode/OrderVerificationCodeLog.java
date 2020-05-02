package com.jinshuo.mall.domain.order.product.orderVerificationCode;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyh
 * @Classname OrderVerificationCode
 * @Description 订单验证码
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVerificationCodeLog extends IdentifiedEntity {

    private OrderVerificationCodeLogId orderVerificationCodeLogId;

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

    public OrderVerificationCodeLog insert() {
        this.orderVerificationCodeLogId = new OrderVerificationCodeLogId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        return this;
    }
}
