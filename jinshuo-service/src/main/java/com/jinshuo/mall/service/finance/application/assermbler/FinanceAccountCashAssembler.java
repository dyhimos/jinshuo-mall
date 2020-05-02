package com.jinshuo.mall.service.finance.application.assermbler;

import com.jinshuo.mall.domain.finance.FinanceAccountCash;
import com.jinshuo.mall.service.finance.application.dto.FinanceCashDto;
import org.springframework.beans.BeanUtils;

/**
 * 资金账户转化类
 */
public class FinanceAccountCashAssembler {

    /**
     * @param financeAccountCash
     * @return
     */
    public static FinanceCashDto assembleDto(FinanceAccountCash financeAccountCash) {
        if (null == financeAccountCash) {
            return null;
        }
        FinanceCashDto dto = new FinanceCashDto();
        BeanUtils.copyProperties(financeAccountCash, dto);
        return dto;
    }
}
