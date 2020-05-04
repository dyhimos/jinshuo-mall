package com.jinshuo.mall.front.order.user;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.order.OcBizException;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.service.order.application.cmd.*;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderIdDto;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderListDto;
import com.jinshuo.mall.service.order.application.qry.GoodsOrderDetailQry;
import com.jinshuo.mall.service.order.application.qry.GoodsOrderQry;
import com.jinshuo.mall.service.order.application.qry.GoodsSimpleQry;
import com.jinshuo.mall.service.order.service.command.GoodsOrderCmdService;
import com.jinshuo.mall.service.order.service.command.GoodsOrderRefundComService;
import com.jinshuo.mall.service.order.service.query.GoodsOrderQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Classname WxGoodsOrderRestApi
 * @Description TODO
 * @Date 2019/6/16 19:52
 * @Created by mgh
 */
@Component
@Slf4j
@RestController
@Validated
@RequestMapping("/v1/wx/gd")
@Api(description = "微信订单接口")
@AllArgsConstructor
public class WxGoodsOrderRestApi {

    @Autowired
    private GoodsOrderQueryService goodsOrderQueryService;

    @Autowired
    private GoodsOrderCmdService goodsOrderCmdService;

    @Autowired
    private GoodsOrderRefundComService goodsOrderRefundComService;


    @PostMapping("/test")
    @ApiOperation("测试完成佣金结算")
    public WrapperResponse create() {
        String s = goodsOrderCmdService.test();
        return WrapperResponse.success(s);
    }

    @PostMapping("/delKey")
    public WrapperResponse delKey() {
        String s = goodsOrderCmdService.delKey();
        return WrapperResponse.success(s);
    }


    /**
     * 查询订单列表
     *
     * @param quert
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("查询订单列表")
    public WrapperResponse queryList(@RequestBody GoodsOrderQry quert) {
        log.info("查询订单列表");
        log.trace("日志输出 trace");
        log.debug("日志输出 debug");
        log.warn("日志输出 warn");
        log.error("日志输出 error");
        PageInfo<GoodsOrderListDto> page = goodsOrderQueryService.queryWxOrderList(quert);
        return WrapperResponse.success(page);
    }


    /**
     * 根据订单id查询订单详情
     *
     * @param goodsOrderDetailQry 订单查询条件
     * @return
     */
    @PostMapping("/queryDetail")
    @ApiOperation("查询订单详情")
    public WrapperResponse queryDetail(@RequestBody GoodsOrderDetailQry goodsOrderDetailQry) {
        GoodsOrderListDto goodsOrderListDto = goodsOrderQueryService.findGoodsOrderById(new GoodsOrderId(goodsOrderDetailQry.getId()));
        return WrapperResponse.success(goodsOrderListDto);
    }


    @PostMapping("/add")
    @ApiOperation("新增订单")
    public WrapperResponse add(@Validated @RequestBody GoodsOrderCreateCmd goodsOrderCreateCmd
    ) throws OcBizException {
        GoodsOrderIdDto goodsOrderIdDto = goodsOrderCmdService.save(goodsOrderCreateCmd);
        return WrapperResponse.success(goodsOrderIdDto);
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
    public WrapperResponse updateStatus(@Validated @RequestBody GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd
    ) {
        goodsOrderCmdService.updateStatus(goodsOrderUpdateStatusCmd);
        return WrapperResponse.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除订单")
    public WrapperResponse delete(@Validated @RequestBody GoodsOrderUpdateCmd goodsOrderUpdateStatusCmd
    ) {
        goodsOrderCmdService.delete(goodsOrderUpdateStatusCmd);
        return WrapperResponse.success();
    }


    @PostMapping("/pay/balance")
    @ApiOperation("余额支付")
    public WrapperResponse balance(@Validated @RequestBody GoodsOrderPayCmd goodsOrderPayCmd
    ) {
        goodsOrderCmdService.balance(goodsOrderPayCmd);
        return WrapperResponse.success();
    }


    @PostMapping("/wx/refund")
    @ApiOperation("微信退款")
    public WrapperResponse refund(@Validated @RequestBody WxRefundCmd wxRefundCmd
    ) throws OcBizException {
        return WrapperResponse.success(goodsOrderCmdService.refund(wxRefundCmd));
    }

    @ApiOperation(httpMethod = "GET", value = "查询本人名下每种状态订单的数量")
    @GetMapping(value = "/getOrderCountWithStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapperResponse deleteCollect() {
        try {
            return WrapperResponse.success(goodsOrderQueryService.queryMyOrderCountWithStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WrapperResponse.fail("网络异常，请稍后再试！");
    }

    @PostMapping("/express/schedule")
    @ApiOperation("物流进度查询")
    public WrapperResponse expressSchedule(@Validated @RequestBody GoodsOrderIdCmd goodsOrderIdCmd
    ) throws Exception {
        return WrapperResponse.success(goodsOrderCmdService.expressSchedule(goodsOrderIdCmd));
    }


    /**
     * 查询订单列表
     *
     * @param quert
     * @return
     */
    @PostMapping("/querySupplierOrderList")
    @ApiOperation("查询订单列表")
    public WrapperResponse querySupplierList(@RequestBody GoodsOrderQry quert) {
        PageInfo<GoodsOrderListDto> page = goodsOrderQueryService.querySupplierList(quert);
        return WrapperResponse.success(page);
    }


    /**
     * 根据订单id查询订单详情
     *
     * @param goodsOrderDetailQry 订单查询条件
     * @return
     */
    @PostMapping("/supplierQueryDetail")
    @ApiOperation("供应商查询订单详情")
    public WrapperResponse supplierQueryDetail(@RequestBody GoodsOrderDetailQry goodsOrderDetailQry) {
        GoodsOrderListDto goodsOrderListDto = goodsOrderQueryService.supplierFindGoodsOrderById(new GoodsOrderId(goodsOrderDetailQry.getId()));
        return WrapperResponse.success(goodsOrderListDto);
    }


    /**
     * 发起退款
     *
     * @param cmd 发起退款
     * @return
     */
    @PostMapping("/applyRefund")
    @ApiOperation("发起退款")
    public WrapperResponse applyRefund(@RequestBody GoodsOrderRefundCmd cmd) throws OcBizException {
        return WrapperResponse.success(goodsOrderRefundComService.applyRefund(cmd));
    }


    /**
     * 新增寄样订单信息
     *
     * @param goodsOrderSimpleCmd
     * @return
     * @throws OcBizException
     */
    @PostMapping("/addSampleOrder")
    @ApiOperation("新增寄样订单信息")
    public WrapperResponse addSimpleOrder(@Validated @RequestBody GoodsOrderSimpleCmd goodsOrderSimpleCmd
    ) throws OcBizException {
        goodsOrderCmdService.addSimpleOrder(goodsOrderSimpleCmd);
        return WrapperResponse.success();
    }


    @PostMapping("/querySampleOrder")
    @ApiOperation("查询寄样订单信息")
    public WrapperResponse addSimpleOrder(@Validated @RequestBody GoodsSimpleQry query) throws OcBizException {
        return WrapperResponse.success(goodsOrderQueryService.queryWxSimpleOrderList(query));
    }


    @GetMapping("/querySampleOrder/{sampleNo}")
    @ApiOperation("查询寄样详情信息")
    public WrapperResponse querySampleOrderDetail(@Validated @PathVariable String sampleNo) {
        return WrapperResponse.success(goodsOrderQueryService.querySampleOrderDetail(sampleNo));
    }

   /* @GetMapping("/delSampleOrder/{sampleNo}")
    @ApiOperation("删除寄样详情信息")
    public WrapperResponse delSampleOrder(@Validated @PathVariable String sampleNo) {
        goodsOrderCmdService.delSampleOrder(sampleNo);
        return WrapperResponse.success();
    }*/
}
