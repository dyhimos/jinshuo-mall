package com.jinshuo.mall.front.finance.user;

import com.jinshuo.core.exception.finance.FcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.finance.application.cmd.FinanceRechargeCmd;
import com.jinshuo.mall.service.finance.service.command.FinanceAccountComService;
import com.jinshuo.mall.service.finance.service.query.FinanceAccountLogQueryService;
import com.jinshuo.mall.service.finance.service.query.FinanceAccountQueryService;
import com.jinshuo.mall.service.order.application.qry.FinanceAccountLogQry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/10.
 */
@RestController
@Api(description = "财务API")
@RequestMapping("/v1/user/finance")
public class WxFinanceRestApi {

    @Autowired
    private FinanceAccountComService financeAccountComService;

    @Autowired
    private FinanceAccountQueryService financeAccountQueryService;

    @Autowired
    private FinanceAccountLogQueryService financeAccountLogQueryService;


    @PostMapping("/recharge")
    @ApiOperation(httpMethod = "POST", value = "消费")
    public WrapperResponse recharge(@Valid @RequestBody FinanceRechargeCmd cmd) throws FcBizException {
        return WrapperResponse.success(financeAccountComService.consumption(cmd));
    }

    @PostMapping("/queryCash")
    @ApiOperation(httpMethod = "POST", value = "查询账户余额")
    public WrapperResponse queryCash() throws FcBizException {
        return WrapperResponse.success(financeAccountQueryService.queryCash());
    }

    @PostMapping("/queryRechargeLog")
    @ApiOperation(httpMethod = "POST", value = "查询个人充值/消费记录")
    public WrapperResponse queryRechargeLog(@RequestBody FinanceAccountLogQry financeAccountLogQry) throws FcBizException {
        return WrapperResponse.success(financeAccountLogQueryService.queryRechargeLog(financeAccountLogQry));
    }
}
