package com.jinshuo.mall.front.order.manager;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.service.order.application.cmd.*;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderListDto;
import com.jinshuo.mall.service.order.application.qry.*;
import com.jinshuo.mall.service.order.service.command.GoodsOrderCmdService;
import com.jinshuo.mall.service.order.service.command.GoodsOrderRefundComService;
import com.jinshuo.mall.service.order.service.query.GoodsOrderQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


/**
 * @Classname SpuRestApi
 * @Description TODO
 * @Date 2019/6/16 19:52
 * @Created by mgh
 */
@Component
@RestController
@Validated
@RequestMapping("/v1/manager/gd")
@Api(description = "订单接口")
@AllArgsConstructor
public class GoodsOrderRestApi {

    @Autowired
    private GoodsOrderQueryService goodsOrderQueryService;

    @Autowired
    private GoodsOrderCmdService goodsOrderCmdService;

    @Autowired
    private GoodsOrderRefundComService goodsOrderRefundComService;



    @PostMapping("/test")
    @ApiOperation("测试服务之间的调用")
    public WrapperResponse create(@RequestBody List<Long> orderIds){
        System.out.println("当前获取到的参数为："+orderIds);
        return WrapperResponse.success();
    }



    /**
     * 查询订单列表
     * @param quert
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("查询订单列表")
    public WrapperResponse create(@RequestBody GoodsOrderQry quert){
        PageInfo<GoodsOrderListDto> page= goodsOrderQueryService.queryManagerList(quert);
        return WrapperResponse.success(page);
    }


    /**
     * 查询订单详情
     * @param goodsOrderDetailQry 订单查询
     * @return
     */
    @PostMapping("/queryDetail")
    @ApiOperation("查询订单详情")
    public WrapperResponse queryDetail(@RequestBody GoodsOrderDetailQry goodsOrderDetailQry){
        GoodsOrderListDto goodsOrderListDto = goodsOrderQueryService.findGoodsOrderById(new GoodsOrderId(goodsOrderDetailQry.getId()));
        return WrapperResponse.success(goodsOrderListDto);
    }


    @PostMapping("/cancelOrder")
    @ApiOperation("取消订单")
    public WrapperResponse cancelOrder(@Validated @RequestBody GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd
    ) {
        goodsOrderCmdService.cancelOrder(goodsOrderUpdateStatusCmd);
        return WrapperResponse.success();
    }


    @PostMapping("/updateStatus")
    @ApiOperation("更新订单状态")
    public WrapperResponse updateStatus(@Validated @RequestBody GoodsOrderUpdateCmd goodsOrderUpdateCmd
    ) {
        goodsOrderCmdService.updateStatus(goodsOrderUpdateCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除订单")
    public WrapperResponse delete(@Validated @RequestBody GoodsOrderUpdateCmd goodsOrderUpdateCmd
    ) {
        goodsOrderCmdService.delete(goodsOrderUpdateCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/upSysRemarks")
    @ApiOperation("更新订单备注")
    public WrapperResponse upSysRemarks(@Validated @RequestBody GoodsOrderUpdateCmd remaksCmd
    ) {
        goodsOrderCmdService.updateSystemRemarks(remaksCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/upExpress")
    @ApiOperation("更新快递信息")
    public WrapperResponse upExpress(@Validated @RequestBody GoodsExpressCmd goodsExpressCmd
    ) throws OcBizException {
        goodsOrderCmdService.upExpress(goodsExpressCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/upVerificationCode")
    @ApiOperation("更新核销码信息")
    public WrapperResponse upVerificationCode(@Validated @RequestBody GoodsVerificationCodeCmd goodsVerificationCodeCmd
    ) throws OcBizException {
        goodsOrderCmdService.upVerificationCode(goodsVerificationCodeCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/finished")
    @ApiOperation("更新订单为已完成")
    public WrapperResponse finished(@Validated @RequestBody GoodsFinshedCmd goodsFinshedCmd
    ) {
        goodsOrderCmdService.finished(goodsFinshedCmd);
        return WrapperResponse.success();
    }


    @PostMapping("/reviewRefundOrder")
    @ApiOperation("审核退款订单单")
    public WrapperResponse reviewRefundOrder(@RequestBody GoodsReviewOrderCmd cmd) throws OcBizException {
        goodsOrderRefundComService.reviewRefundOrder(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/refundOrder")
    @ApiOperation("退款订单打款")
    public WrapperResponse refundOrder(@RequestBody GoodsReviewOrderCmd cmd) throws OcBizException {
        goodsOrderRefundComService.refundOrder(cmd);
        return WrapperResponse.success();
    }

    @PostMapping("/countOrder")
    @ApiOperation("分类统计订单")
    public WrapperResponse countOrder(@RequestBody ManagerOrderCountQry qry) {
        return WrapperResponse.success(goodsOrderQueryService.countOrder(qry));
    }

    @PostMapping("/countOrderTotal")
    @ApiOperation("分类统计订单（总）")
    public WrapperResponse countOrderTotal(@RequestBody ManagerOrderCountQry qry) {
        return WrapperResponse.success(goodsOrderQueryService.countOrderTotal(qry));
    }


    @PostMapping("/countOrderAmount")
    @ApiOperation("分类统计订单金额")
    public WrapperResponse countOrderAmount(@RequestBody ManagerOrderCountAmountQry qry) throws ParseException {
        return WrapperResponse.success(goodsOrderQueryService.countOrderAmount(qry));
    }


    @PostMapping("/countOrderByDate")
    @ApiOperation("分类统计订单")
    public WrapperResponse countOrderByDate(@RequestBody PageQry qry) {
        return WrapperResponse.success(goodsOrderQueryService.countOrderByDate(qry));
    }


    @PostMapping("/querySampleOrder")
    @ApiOperation("查询寄样信息")
    public WrapperResponse querySampleOrder(@Validated @RequestBody GoodsSimpleQry query
    ) throws OcBizException {
        return WrapperResponse.success(goodsOrderQueryService.querySimpleOrderList(query));
    }


   /* @PostMapping("/upSampleOrderExpress")
    @ApiOperation("更新寄样信息快递信息")
    public WrapperResponse upSampleOrderExpress(@Validated @RequestBody GoodsExpressCmd goodsExpressCmd
    ) throws OcBizException {
        goodsOrderCmdService.upSampleOrderExpress(goodsExpressCmd);
        return WrapperResponse.success();
    }

    @GetMapping("/delSampleOrder/{sampleNo}")
    @ApiOperation("删除寄样详情信息")
    public WrapperResponse delSampleOrder(@Validated @PathVariable String sampleNo){
        goodsOrderCmdService.delSampleOrder(sampleNo);
        return WrapperResponse.success();
    }*/
}
