package com.jinshuo.mall.service.finance.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.finance.FinanceAccountCash;
import com.jinshuo.mall.domain.finance.FinanceAccountCashId;
import com.jinshuo.mall.service.finance.application.dto.FinanceCashPageDto;
import com.jinshuo.mall.service.finance.application.qry.FinanceAccountCashQry;
import com.jinshuo.mall.service.finance.mybatis.mapper.FinanceAccountCashMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FinanceAccountCashRepo {

    @Autowired
    private FinanceAccountCashMapper financeAccountCashMapper;

    public FinanceAccountCashId nextId(){
        return new FinanceAccountCashId(CommonSelfIdGenerator.generateId());
    }

    public int create(FinanceAccountCash financeAccountCash) {
        return financeAccountCashMapper.create(financeAccountCash);
    }

    public int recharge(FinanceAccountCash financeAccountCash) {
        return financeAccountCashMapper.recharge(financeAccountCash);
    }

    public FinanceAccountCash findAccountByMemberId(Long memberId) {
        return financeAccountCashMapper.queryByMemberId(memberId);
    }

    public int deleteAccountById(Long id) {
        return financeAccountCashMapper.deleteById(id);
    }

    /**
     * 查询账户列表
     * @param query
     * @return
     */
    public List<FinanceCashPageDto> queryFinanceAccountList(FinanceAccountCashQry query){
        return financeAccountCashMapper.queryFinanceAccountList(query);
    }

}