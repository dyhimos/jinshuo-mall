package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.order.product.order.*;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import com.jinshuo.mall.service.order.application.dto.CountOrderDto;
import com.jinshuo.mall.service.order.application.dto.OrderStatusDto;
import com.jinshuo.mall.service.order.application.qry.GoodsOrderQry;
import com.jinshuo.mall.service.order.application.qry.ManagerOrderCountQry;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname GoodsOrderMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsOrderMapper {

    @InsertProvider(type = DynamicSql.class, method = "createGoodsOrderSql")
    void save(GoodsOrder goodsOrder);


    @ResultMap(value = "goodsOrderList")
    @Select("SELECT * FROM goods_order WHERE id=#{id}")
    GoodsOrder findGoodsOrderById(GoodsOrderId goodsOrderId);

    @ResultMap(value = "goodsOrderList")
    @Select("SELECT * FROM goods_order WHERE order_no=#{orderNo}")
    GoodsOrder findGoodsOrderByOrderNo(GoodsOrder goodsOrder);

    @Results(
            id = "goodsOrderList",
            value = {
                    @Result(property = "goodsOrderId.id", column = "id"),
                    @Result(property = "orderStatus", column = "order_status", javaType = OrderStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "payChannel", column = "pay_channel", javaType = PayChannelEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "payStatus", column = "pay_status", javaType = OrderPayStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "settlementStatus", column = "settlement_status", javaType = SettlementStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "orderCode", column = "order_code"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "orderAmountTotal", column = "order_amount_total"),
                    @Result(property = "logisticsFee", column = "logistics_fee"),
                    @Result(property = "couponAmount", column = "coupon_amount"),
                    @Result(property = "memberId", column = "member_id"),
                    @Result(property = "inviteCode", column = "invite_code"),
                    @Result(property = "memberName", column = "member_name"),
                    @Result(column = "id", property = "goodsOrderDetailList", javaType = List.class,
                            many = @Many(select = "com.ym.wool.oc.infra.mybatis.mapper.GoodsOrderDetailMapper.selectByOrderId")),
                    @Result(column = "id", property = "goodsOrderCouponList", javaType = List.class,
                            many = @Many(select = "com.ym.wool.oc.infra.mybatis.mapper.GoodsOrderCouponMapper.findByOrderId")),
                    @Result(column = "id", property = "goodsOrderAddress", javaType = GoodsOrderAddress.class,
                            one = @One(select = "com.ym.wool.oc.infra.mybatis.mapper.GoodsOrderAddressMapper.findByOrderId")),
                    @Result(column = "id", property = "goodsOrderExpress", javaType = GoodsOrderExpress.class,
                            one = @One(select = "com.ym.wool.oc.infra.mybatis.mapper.GoodsExpressMapper.findGoodsExpressById")),

            }
    )
    @SelectProvider(type = DynamicSql.class, method = "queryGoodsOrderSql")
    List<GoodsOrder> queryGoodsOrder(GoodsOrderQry query);

    @UpdateProvider(type = DynamicSql.class, method = "updateGoodsOrderSql")
    void update(GoodsOrder goodsOrder);

    /**
     * 完成订单
     *
     * @param goodsOrderIdCmdList
     */
    @Update({"<script>" +
            "update goods_order set order_status=4,update_date=now() where id in"
            + "<foreach  collection = 'goodsOrderIdCmdList' item = 'id'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{id} "
            + "</foreach>"
            + "</script>"})
    void finshed(@Param("goodsOrderIdCmdList") List<Long> goodsOrderIdCmdList);


    @Update("UPDATE  goods_order SET order_status=#{status} " +
            " WHERE id = #{orderId} ")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("status") Integer status);

    @Results(
            id = "goodsOrders",
            value = {
                    @Result(property = "goodsOrderId.id", column = "id"),
                    @Result(property = "orderStatus", column = "order_status", javaType = OrderStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "exchangeStatus", column = "exchange_status", javaType = ExchangeStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "payChannel", column = "pay_channel", javaType = PayChannelEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "settlementStatus", column = "settlement_status", javaType = SettlementStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "orderCode", column = "order_code"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "payStatus", column = "pay_status", javaType = OrderPayStatusEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "orderAmountTotal", column = "order_amount_total"),
                    @Result(property = "logisticsFee", column = "logistics_fee"),
                    @Result(property = "couponAmount", column = "coupon_amount"),
                    @Result(property = "memberId", column = "member_id"),
                    @Result(property = "inviteCode", column = "invite_code"),
                    @Result(property = "memberName", column = "member_name"),
            }
    )
    @SelectProvider(type = DynamicSql.class, method = "countOrder")
    List<GoodsOrder> countOrder(ManagerOrderCountQry qry);


    @Results(
            id = "goodStatus",
            value = {
                    @Result(property = "orderStatus", column = "orderStatus"),
                    @Result(property = "count", column = "count")
            }
    )
    @Select("SELECT order_status as orderStatus,count(id) as count FROM  goods_order" +
            " WHERE shop_id = #{shopId} GROUP BY order_status ")
    List<OrderStatusDto> historyCount(@Param("shopId") Long shopId);

    @ResultType(BigDecimal.class)
    @Select("<script>" +
            "SELECT order_amount_total FROM goods_order WHERE pay_status=0 " +
            "<if test='type == 1'> " +
            "and DATE_FORMAT(pay_time,'%Y%m') =DATE_FORMAT(#{date},'%Y%m')" +
            "</if>" +
            "<if test='type == 2'> " +
            "and TO_DAYS(pay_time) =TO_DAYS(#{date})" +
            "</if>" +
            "</script>")
    List<BigDecimal> countOrderAmount(@Param("date") Date date, @Param("type") Integer type);

    @Results(
            id = "CountOrderDtoResult",
            value = {
                    @Result(property = "date", column = "create_date"),
                    @Result(property = "orderCount", column = "count"),
                    @Result(property = "payCount", column = "pay_count"),
            }
    )
    @Select("SELECT TO_DAYS(create_date),create_date,COUNT(1) as count , SUM(CASE WHEN pay_status=0 THEN 1 ELSE 0 END ) as pay_count " +
            "FROM goods_order GROUP BY TO_DAYS(create_date) ORDER BY create_date DESC ")
    List<CountOrderDto> countOrderByDate();
}
