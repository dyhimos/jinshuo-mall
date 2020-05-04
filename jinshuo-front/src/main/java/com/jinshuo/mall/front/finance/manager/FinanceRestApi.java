package com.jinshuo.mall.front.finance.manager;

import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.finance.application.cmd.FinanceRechargeCmd;
import com.jinshuo.mall.service.finance.application.qry.FinanceAccountCashQry;
import com.jinshuo.mall.service.finance.service.command.FinanceAccountComService;
import com.jinshuo.mall.service.finance.service.query.FinanceAccountLogQueryService;
import com.jinshuo.mall.service.finance.service.query.FinanceAccountQueryService;
import com.jinshuo.mall.service.order.application.qry.FinanceAccountLogQry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/10.
 */
@RestController
@Api(description = "财务API")
@RequestMapping("/v1/manager/finance")
public class FinanceRestApi {

    @Autowired
    private FinanceAccountComService financeAccountComService;

    @Autowired
    private FinanceAccountLogQueryService financeAccountLogQueryService;


    @Autowired
    private FinanceAccountQueryService financeAccountQueryService;


    @PostMapping("/recharge")
    @ApiOperation(httpMethod = "POST", value = "后台充值")
    public WrapperResponse recharge(@Valid @RequestBody FinanceRechargeCmd cmd) {
        financeAccountComService.recharge(cmd);
        return WrapperResponse.success();
    }

    @GetMapping("/createFinanceAccount")
    @ApiOperation(httpMethod = "GET", value = "创建财务账号")
    public WrapperResponse createFinanceAccount() {
        return WrapperResponse.success(financeAccountComService.create());
    }

    @PostMapping("/queryAccountList")
    @ApiOperation(httpMethod = "POST", value = "查询账户列表")
    public WrapperResponse queryFinanceAccountList(@Valid @RequestBody FinanceAccountCashQry qry) {
        return WrapperResponse.success(financeAccountQueryService.queryFinanceAccountList(qry));
    }


    @PostMapping("/queryRechargeLog")
    @ApiOperation(httpMethod = "POST", value = "查询个人充值/消费记录")
    public WrapperResponse queryRechargeLog(@RequestBody FinanceAccountLogQry financeAccountLogQry) throws FcBizException {
        return WrapperResponse.success(financeAccountLogQueryService.queryBackRechargeLog(financeAccountLogQry));
    }
}
