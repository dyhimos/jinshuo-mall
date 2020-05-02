package com.jinshuo.mall.service.finance.mybatis;

import com.jinshuo.mall.domain.finance.FinanceAccountLog;
import com.jinshuo.mall.service.finance.mybatis.mapper.FinanceAccountLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FinanceAccountLogRepo {

    @Autowired
    private FinanceAccountLogMapper financeAccountLogMapper;

    public int create(FinanceAccountLog financeAccountLog) {
        return financeAccountLogMapper.create(financeAccountLog);
    }

    public List<FinanceAccountLog> findAccountLogsById(Long memberId) {
        return financeAccountLogMapper.queryByMemberId(memberId);
    }

    public List<FinanceAccountLog> findAccountRechargeLogs(FinanceAccountLog financeAccountLog) {
        return financeAccountLogMapper.findAccountRechargeLogs(financeAccountLog);
    }

    public int deleteAccountById(Long id) {
        return financeAccountLogMapper.deleteById(id);
    }

}