package com.jinshuo.mall.service.finance.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.exception.finance.FcReturnCode;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.finance.FinanceAccountCash;
import com.jinshuo.mall.service.finance.application.assermbler.FinanceAccountCashAssembler;
import com.jinshuo.mall.service.finance.application.dto.FinanceCashDto;
import com.jinshuo.mall.service.finance.application.dto.FinanceCashPageDto;
import com.jinshuo.mall.service.finance.application.qry.FinanceAccountCashQry;
import com.jinshuo.mall.service.finance.mybatis.FinanceAccountCashRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 *
 * @author dyh
 */
@Slf4j
@Service
public class FinanceAccountQueryService {

    @Autowired
    private FinanceAccountCashRepo financeAccountCashRepo;


    /**
     * 查询单账户的资金状况
     *
     * @return
     * @throws FcBizException
     */
    public FinanceCashDto queryCash() throws FcBizException {
        Long userId = UserIdUtils.getUserId();
        log.info(" 查询会员账户资金,会员编号为：{}", userId);
        FinanceAccountCash cash = financeAccountCashRepo.findAccountByMemberId(userId);
        if (cash == null) {
            throw new FcBizException(FcReturnCode.IC206022.getCode(), FcReturnCode.IC206022.getMsg());
        } else {
            if (1 == cash.getAccountStatus()) {
                throw new FcBizException(FcReturnCode.IC206023.getCode(), FcReturnCode.IC206023.getMsg());
            }
        }

        return FinanceAccountCashAssembler.assembleDto(cash);
    }


    /**
     * 查询资金账户列表
     *
     * @param query
     * @return
     */
    public PageInfo<FinanceCashPageDto> queryFinanceAccountList(FinanceAccountCashQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<FinanceCashPageDto> FinanceCashPageDtoList = financeAccountCashRepo.queryFinanceAccountList(query);
        PageInfo pageInfo = new PageInfo<>(FinanceCashPageDtoList);
        return pageInfo;
    }
}
