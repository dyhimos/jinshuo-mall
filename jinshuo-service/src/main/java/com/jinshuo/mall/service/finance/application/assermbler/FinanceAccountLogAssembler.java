package com.jinshuo.mall.service.finance.application.assermbler;

import com.jinshuo.mall.domain.finance.FinanceAccountLog;
import com.jinshuo.mall.service.finance.application.dto.FinanceAccountLogDto;
import org.springframework.beans.BeanUtils;

/**
 * 资金账户转化类
 */
public class FinanceAccountLogAssembler {

    /**
     * @param financeAccountLog
     * @return
     */
    public static FinanceAccountLogDto assembleDto(FinanceAccountLog financeAccountLog) {
        if (null == financeAccountLog) {
            return null;
        }
        FinanceAccountLogDto dto = new FinanceAccountLogDto();
        BeanUtils.copyProperties(financeAccountLog, dto);
        dto.setId(financeAccountLog.getFinanceAccountLogId().getId());
        dto.setCreateDate(financeAccountLog.getCreateDate());
        return dto;
    }
}
