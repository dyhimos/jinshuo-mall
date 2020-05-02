package com.jinshuo.mall.service.finance.service.command;

import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.exception.finance.FcReturnCode;
import com.jinshuo.core.model.UserAuthDto;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.finance.FinanceAccountCash;
import com.jinshuo.mall.domain.finance.FinanceAccountLog;
import com.jinshuo.mall.domain.finance.FinanceRecharge;
import com.jinshuo.mall.service.finance.application.cmd.FinanceRechargeCmd;
import com.jinshuo.mall.service.finance.mybatis.FinanceAccountCashRepo;
import com.jinshuo.mall.service.finance.mybatis.FinanceAccountLogRepo;
import com.jinshuo.mall.service.finance.mybatis.FinanceRechargeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/12/25.
 *
 * @author dyh
 */
@Slf4j
@Service
public class FinanceAccountComService {

    @Autowired
    private FinanceAccountCashRepo financeAccountCashRepo;

    @Autowired
    private FinanceAccountLogRepo financeAccountLogRepo;

    @Autowired
    private FinanceRechargeRepo financeRechargeRepo;


    /**
     * 创建我的账户
     *
     * @return
     */
    public int create() throws FcBizException{
        log.info(" -- 创建我的账户");
        UserAuthDto userAuthDto = UserIdUtils.getUser();
        FinanceAccountCash cash = financeAccountCashRepo.findAccountByMemberId(Long.parseLong(userAuthDto.getId()));
        if (null != cash) {
            throw new FcBizException(FcReturnCode.IC206021.getCode(), FcReturnCode.IC206021.getMsg());
        }
        FinanceAccountCash financeAccountCash = FinanceAccountCash.builder()
                .memId(Long.parseLong(userAuthDto.getId()))
                .shopId(userAuthDto.getShopId())
                .build();
        financeAccountCash.init();
        return financeAccountCashRepo.create(financeAccountCash);
    }

    /**
     * 充值
     *
     * @returnr
     */
    @Transactional(rollbackFor = Exception.class)
    public int recharge(FinanceRechargeCmd cmd) throws FcBizException{
        log.info(" -- 充值，输入参数，{}", cmd);
        FinanceAccountCash financeAccountCash = financeAccountCashRepo.findAccountByMemberId(cmd.getMemberId());
        if (null == financeAccountCash) {
            throw new FcBizException(FcReturnCode.IC206022.getCode(), FcReturnCode.IC206022.getMsg());
        }
        FinanceAccountLog financeAccountLog = FinanceAccountLog.builder()
                .memId(cmd.getMemberId())
                .shopId(financeAccountCash.getShopId())
                .beforeChangeAmount(financeAccountCash.getTotalAmount())
                .afterChangeAmount(financeAccountCash.getTotalAmount().add(cmd.getAmount()))
                .changeAmount(cmd.getAmount())
                .type(0)
                .source(0)
                .desc("后台充值")
                .sourceSn(cmd.getSn())
                .build();
        financeAccountLog.init();
        financeAccountCash.recharge(cmd.getAmount());
        FinanceRecharge financeRecharge = FinanceRecharge.builder()
                .amount(cmd.getAmount())
                .memId(cmd.getMemberId())
                .shopId(financeAccountCash.getShopId())
                .sn(cmd.getSn())
                .type(cmd.getType())
                .build();
        financeRecharge.init();
        financeAccountLogRepo.create(financeAccountLog);
        financeRechargeRepo.create(financeRecharge);
        return financeAccountCashRepo.recharge(financeAccountCash);
    }

    /**
     * 消费
     *
     * @returnr
     */
    @Transactional(rollbackFor = Exception.class)
    public int consumption(FinanceRechargeCmd cmd) throws FcBizException{
        log.info(" -- 消费，输入参数，{}", cmd);

        FinanceAccountCash financeAccountCash = financeAccountCashRepo.findAccountByMemberId(cmd.getMemberId());
        //如果为空，则账户不存在
        if(financeAccountCash == null){
            throw new FcBizException(FcReturnCode.IC206021.getCode(),FcReturnCode.IC206021.getMsg());
        }

        BigDecimal beforeChangeAmount = financeAccountCash.getTotalAmount();

        BigDecimal afterChangeAmount = financeAccountCash.getTotalAmount();

        BigDecimal changeAmount = new BigDecimal(0);

        //如果是余额支付则更新余额账户
        if(cmd.getType() ==1){
            financeAccountCash.consumption(cmd.getAmount());
            financeAccountCashRepo.recharge(financeAccountCash);

            //余额支付更新之前的金额和之后的金额
            beforeChangeAmount = financeAccountCash.getTotalAmount().add(cmd.getAmount());
            changeAmount = cmd.getAmount();
        }

        FinanceAccountLog financeAccountLog = FinanceAccountLog.builder()
                .memId(cmd.getMemberId())
                .shopId(financeAccountCash.getShopId())
                .beforeChangeAmount(beforeChangeAmount)
                .afterChangeAmount(afterChangeAmount)
                .changeAmount(changeAmount)
                .type(1)
                .source(cmd.getType())
                .desc("客户消费")
                .sourceSn(cmd.getSn())
                .build();
        financeAccountLog.init();
        return financeAccountLogRepo.create(financeAccountLog);
    }

}
