package com.jinshuo.mall.service.finance.mybatis;

import com.jinshuo.mall.domain.finance.FinanceRecharge;
import com.jinshuo.mall.service.finance.mybatis.mapper.FinanceRechargeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FinanceRechargeRepo {

    @Autowired
    private FinanceRechargeMapper financeRechargeMapper;

    public int create(FinanceRecharge financeRecharge) {
        return financeRechargeMapper.create(financeRecharge);
    }

    public List<FinanceRecharge> findAccountLogsById(Long memberId) {
        return financeRechargeMapper.queryByMemberId(memberId);
    }

    public int deleteAccountById(Long id) {
        return financeRechargeMapper.deleteById(id);
    }

}