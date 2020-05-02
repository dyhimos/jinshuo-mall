package com.jinshuo.mall.domain.finance;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceRecharge extends IdentifiedEntity {
    private FinanceRechargeId financeRechargeId;

    private Long shopId;

    /**
     * 充值流水号
     */
    private String sn;

    private Long memId;
    /**
     * 充值类型 0 后台充值
     */
    private Integer type;
    /**
     * 充值金额
     */
    private BigDecimal amount;

    public void init(){
        this.financeRechargeId = new FinanceRechargeId(CommonSelfIdGenerator.generateId());
        super.preInsert();
    }
}