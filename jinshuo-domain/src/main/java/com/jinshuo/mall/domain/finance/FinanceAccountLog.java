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
public class FinanceAccountLog extends IdentifiedEntity {
    private FinanceAccountLogId financeAccountLogId;

    private Long shopId;

    private Long memId;

    /**
     * 类型 0:收入,1:支出
     */
    private Integer type;
    /**
     * 记录来源 0:后台充值 1余额支出 2：支付宝支付 3：微信支付
     */
    private Integer source;
    /**
     * 相关单据流水号
     */
    private String sourceSn;
    /**
     * 总账户变动之前余额
     */
    private BigDecimal beforeChangeAmount;
    /**
     * 总账户变动之后余额
     */
    private BigDecimal afterChangeAmount;
    /**
     * 变动金额
     */
    private BigDecimal changeAmount;
    /**
     * 具体变动描述
     */
    private String desc;

    public void init(){
        super.preInsert();
        this.financeAccountLogId = new FinanceAccountLogId(CommonSelfIdGenerator.generateId());
    }
}