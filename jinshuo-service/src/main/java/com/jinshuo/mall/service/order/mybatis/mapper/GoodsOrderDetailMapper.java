package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.service.order.application.qry.OrderCountQry;
import com.jinshuo.mall.service.order.application.qry.OrderDetailQryDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname GoodsOrderDetailMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsOrderDetailMapper {

    /**
     * 根据订单id查询订单详细信息
     *
     * @param goodsOrderId
     * @return
     */
    @Results(
            id = "goodsOrderDetail",
            value = {
                    @Result(property = "goodsOrderDetailId.id", column = "id"),
                    @Result(property = "goodsOrderId.id", column = "order_id"),
                    @Result(property = "supplierId", column = "supplier_id"),
                    @Result(property = "supplierName", column = "supplier_name"),
                    @Result(property = "goodsSpuId", column = "goods_spu_id"),
                    @Result(property = "goodsName", column = "goods_name"),
                    @Result(property = "skuName", column = "sku_name"),
                    @Result(property = "goodsImage", column = "goods_image"),
                    @Result(property = "goodsPrice", column = "goods_price"),
                    @Result(property = "costPrice", column = "cost_price"),
                    @Result(property = "goodsSkuId", column = "goods_sku_id"),
                    @Result(property = "discountRate", column = "discount_rate"),
                    @Result(property = "discountAmount", column = "discount_amount"),
                    @Result(property = "number", column = "number"),
                    @Result(property = "autoSendCode", column = "auto_send_code"),
                    @Result(property = "isSendcode", column = "is_sendcode"),
                    @Result(property = "reserveAddress", column = "reserve_address"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(column = "id", property = "verificationCodeList", javaType = List.class,
                            many = @Many(select = "com.ym.wool.oc.infra.mybatis.mapper.GoodsVerificationCodeMapper.selectByOrderDetailId")),
                    @Result(property = "subtotal", column = "subtotal")
            }
    )
    @SelectProvider(type = OrderDynamicSql.class, method = "queryGoodsOrderDetailSql")
    List<GoodsOrderDetail> findByOrderId(GoodsOrderId goodsOrderId);


    /**
     * 根据订单Id查询订单详情
     *
     * @param goodsOrderId
     * @return
     */
    @ResultMap(value = "goodsOrderDetail")
    @Select("select * from goods_order_detail where order_id=#{goodsOrderId}")
    List<GoodsOrderDetail> selectByOrderId(Long goodsOrderId);

    /**
     * 保存订单产品信息
     *
     * @param goodsOrderDetail
     */
    @InsertProvider(type = OrderDynamicSql.class, method = "createGoodsOrderDetailSql")
    void save(GoodsOrderDetail goodsOrderDetail);

    /**
     * 根据供应商id查询订单详情
     *
     * @param qry
     */
    @Results(
            id = "orderDetailQryDto",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "orderNo", column = "orderNo"),
                    @Result(property = "orderType", column = "orderType"),
                    @Result(property = "memberId", column = "memberId"),
                    @Result(property = "memberName", column = "memberName"),
                    @Result(property = "orderStatus", column = "orderStatus"),
                    @Result(property = "afterStatus", column = "afterStatus"),
                    @Result(property = "payChannel", column = "payChannel"),
                    @Result(property = "orderSettlementTime", column = "orderSettlementTime"),
                    @Result(property = "supplierId", column = "supplierId"),
                    @Result(property = "supplierName", column = "supplierName"),
                    @Result(property = "logisticsFee", column = "logisticsFee"),
                    @Result(property = "goodsSpuId", column = "goodsSpuId"),
                    @Result(property = "goodsName", column = "goodsName"),
                    @Result(property = "goodsPrice", column = "goodsPrice"),
                    @Result(property = "goodsSkuId", column = "goodsSkuId"),
                    @Result(property = "number", column = "number"),
                    @Result(property = "subtotal", column = "subtotal"),
                    @Result(property = "isSendcode", column = "isSendcode"),
                    @Result(property = "autoSendCode", column = "autoSendCode"),
                    @Result(property = "costPrice", column = "costPrice")
            }
    )
    @SelectProvider(type = OrderDynamicSql.class, method = "queryGoodsOrderDetailBySupplierIdSql")
    List<OrderDetailQryDto> queryBySupplierId(OrderCountQry qry);


    /**
     * 根据订单Id查询订单详情
     *
     * @param id
     * @return
     */
    @ResultMap(value = "goodsOrderDetail")
    @Select("select * from goods_order_detail where id=#{id}")
    GoodsOrderDetail queryById(Long id);

}
