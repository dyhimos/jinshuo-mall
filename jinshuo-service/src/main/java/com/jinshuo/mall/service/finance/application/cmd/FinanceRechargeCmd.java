package com.jinshuo.mall.service.finance.application.cmd;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinanceRechargeCmd {
    private Long memberId;
    private String sn;
    private Integer type;
    private BigDecimal amount;
}