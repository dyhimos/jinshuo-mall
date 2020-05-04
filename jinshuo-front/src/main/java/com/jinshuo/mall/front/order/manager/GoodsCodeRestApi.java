package com.jinshuo.mall.front.order.manager;

import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.order.application.cmd.WriteOffVerificationCodeCmd;
import com.jinshuo.mall.service.order.application.qry.LogQry;
import com.jinshuo.mall.service.order.application.qry.OrderCountQry;
import com.jinshuo.mall.service.order.service.command.GoodsOrderCmdService;
import com.jinshuo.mall.service.order.service.query.GoodsOrderQueryService;
import com.jinshuo.mall.service.order.service.query.OrderVerificationCodeLogQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 19458 on 2019/11/19.
 */

@Component
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/manager/code")
@Api(description = "自有供应商后台")
public class GoodsCodeRestApi {

    @Autowired
    private GoodsOrderCmdService goodsOrderCmdService;

    @Autowired
    private GoodsOrderQueryService goodsOrderQueryService;

    @Autowired
    private OrderVerificationCodeLogQueryService logQueryService;


    @PostMapping("/getMyCodeInfo")
    @ApiOperation("统计我的产品销售记录")
    public WrapperResponse create(@RequestBody OrderCountQry qry) throws OcBizException {
        return WrapperResponse.success(goodsOrderQueryService.countPerformance(qry));
    }


    @PostMapping("/writeOff")
    @ApiOperation("核销产品卷")
    public WrapperResponse writeOff(@RequestBody WriteOffVerificationCodeCmd cmd) throws OcBizException {
        return WrapperResponse.success(goodsOrderCmdService.writeOffCode(cmd));
    }

    @PostMapping("/getWriteOffLogPage")
    @ApiOperation("分页查询核销记录")
    public WrapperResponse getWriteOffLog(@RequestBody LogQry qry) throws OcBizException {
        return WrapperResponse.success(logQueryService.getByPage(qry));
    }

    @PostMapping("/getLogBySpuId")
    @ApiOperation("分页查询核销记录(根据产品)")
    public WrapperResponse getLogBySpuId(@RequestBody LogQry qry) throws OcBizException {
        return WrapperResponse.success(logQueryService.getLogBySpuId(qry));
    }

    @GetMapping("/testSendCod/{oderNo}")
    @ApiOperation("测试发码")
    public WrapperResponse testSendCod(@PathVariable(value = "oderNo") String oderNo) throws OcBizException {
        return WrapperResponse.success(goodsOrderCmdService.testSendCode(oderNo));
    }
}
