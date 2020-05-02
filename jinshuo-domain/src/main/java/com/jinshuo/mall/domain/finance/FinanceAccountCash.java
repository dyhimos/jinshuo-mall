package com.jinshuo.mall.domain.finance;

import com.jinshuo.core.exception.JsException;
import com.jinshuo.core.exception.JsReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceAccountCash extends IdentifiedEntity {

    private final static BigDecimal ZERO = new BigDecimal(0);
    private FinanceAccountCashId financeAccountCashId;

    private Long shopId;

    private Long memId;
    /**
     * 可用余额
     */
    private BigDecimal avaibleAmount;
    /**
     * 累计余额
     */
    private BigDecimal totalAmount;
    /**
     * 冻结余额
     */
    private BigDecimal frozenAmount;
    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * 充值次数
     */
    private Integer rechargeTime;
    /**
     * 账户状态 0正常 1停用 FinanceAccountStatusEnums
     */
    private Integer accountStatus;
    /**
     * 变动时间
     */
    private Date addTime;


    /**
     * 账户初始化
     */
    public void init() {
        this.financeAccountCashId = new FinanceAccountCashId(CommonSelfIdGenerator.generateId());
        super.preInsert();
        this.addTime = new Date();
        this.avaibleAmount = ZERO;
        this.totalAmount = ZERO;
        this.frozenAmount = ZERO;
        this.rechargeAmount = ZERO;
        this.rechargeTime = 0;
        this.accountStatus = FinanceAccountStatusEnums.FINANCE_ACCOUNT_NORMAL.code;
    }

    public void recharge(BigDecimal avaibleAmount) throws JsException {
        if (!FinanceAccountStatusEnums.FINANCE_ACCOUNT_NORMAL.code.equals(this.accountStatus)) {
            throw new JsException(JsReturnCode.SYS100001.getCode(), JsReturnCode.SYS100001.getMsg());
        }
        if (ZERO.compareTo(avaibleAmount) == 1) {
            throw new JsException(JsReturnCode.SYS100001.getCode(), JsReturnCode.SYS100001.getMsg());
        }
        this.avaibleAmount = this.avaibleAmount.add(avaibleAmount);
        this.totalAmount = this.totalAmount.add(avaibleAmount);
        this.rechargeTime = this.rechargeTime + 1;
        this.rechargeAmount = this.rechargeAmount.add(avaibleAmount);
        this.addTime = new Date();
    }

    public void consumption(BigDecimal amount) throws JsException {
        if (!FinanceAccountStatusEnums.FINANCE_ACCOUNT_NORMAL.code.equals(this.accountStatus)) {
            throw new JsException(JsReturnCode.SYS100001.getCode(), JsReturnCode.SYS100001.getMsg());
        }
        if (this.avaibleAmount.compareTo(amount) == -1 || this.totalAmount.compareTo(amount) == -1) {
            throw new JsException(JsReturnCode.SYS100001.getCode(), JsReturnCode.SYS100001.getMsg());
        }
        this.avaibleAmount = this.avaibleAmount.subtract(amount);
        this.totalAmount = this.totalAmount.subtract(amount);
        this.addTime = new Date();
    }
}