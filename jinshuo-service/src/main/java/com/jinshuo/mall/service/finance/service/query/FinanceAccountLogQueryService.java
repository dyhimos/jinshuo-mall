package com.jinshuo.mall.service.finance.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.finance.FinanceAccountLog;
import com.jinshuo.mall.service.finance.application.assermbler.FinanceAccountLogAssembler;
import com.jinshuo.mall.service.finance.application.dto.FinanceAccountLogDto;
import com.jinshuo.mall.service.finance.mybatis.FinanceAccountLogRepo;
import com.jinshuo.mall.service.order.application.qry.FinanceAccountLogQry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 充值日志
 *
 * @author dyh
 */
@Slf4j
@Service
public class FinanceAccountLogQueryService {

    @Autowired
    private FinanceAccountLogRepo financeAccountLogRepo;


    /**
     * 查询单账户的资金状况
     * @return
     * @throws FcBizException
     */
    public  PageInfo queryRechargeLog(FinanceAccountLogQry qry) throws FcBizException {
        Long userId = UserIdUtils.getUserId();
        log.info(" 查询会员账户充值记录,会员编号为：{}",userId);
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        FinanceAccountLog financeAccountLog = FinanceAccountLog.builder()
                .memId(userId)
                .source(qry.getSource())
                .type(qry.getType())
                .build();
        List<FinanceAccountLog> financeAccountLogs = financeAccountLogRepo.findAccountRechargeLogs(financeAccountLog);
        List<FinanceAccountLogDto> financeAccountLogDtos = financeAccountLogs.stream().map(t-> FinanceAccountLogAssembler.assembleDto(t)).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo<>(financeAccountLogDtos);
        return pageInfo;
    }


    /**
     * 后台查询用户的充值记录
     * @return
     * @throws FcBizException
     */
    public  PageInfo queryBackRechargeLog(FinanceAccountLogQry qry) throws FcBizException{
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        FinanceAccountLog financeAccountLog = FinanceAccountLog.builder()
                .source(qry.getSource())
                .type(qry.getType())
                .build();
        List<FinanceAccountLog> financeAccountLogs = financeAccountLogRepo.findAccountRechargeLogs(financeAccountLog);
        List<FinanceAccountLogDto> financeAccountLogDtos = financeAccountLogs.stream().map(t-> FinanceAccountLogAssembler.assembleDto(t)).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo<>(financeAccountLogDtos);
        return pageInfo;
    }
}
