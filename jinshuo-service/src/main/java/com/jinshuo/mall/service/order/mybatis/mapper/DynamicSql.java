package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.product.order.GoodsOrder;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.domain.order.product.orderCoupon.GoodsOrderCoupon;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import com.jinshuo.mall.service.order.application.qry.GoodsOrderQry;
import com.jinshuo.mall.service.order.application.qry.GoodsSimpleQry;
import com.jinshuo.mall.service.order.application.qry.ManagerOrderCountQry;
import com.jinshuo.mall.service.order.application.qry.OrderCountQry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author dongyh
 * @Title: Test
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/17 15:06
 */
@Slf4j
public class DynamicSql {
    /**
     * 保存sql
     *
     * @param goodsOrder
     * @return
     */
    public String createGoodsOrderSql(GoodsOrder goodsOrder) {
        log.info("进入创建sql");
        return new SQL()
                .INSERT_INTO("goods_order")
                .INTO_COLUMNS("id", "order_no", "order_type", "member_id", "shop_id", "invite_code", "member_name", "supplier_id", "order_status", "after_status",
                        "goods_count",
                        "goods_amount_total",
                        "logistics_fee",
                        "coupon_amount",
                        "order_amount_total",
                        "address_id",
                        "pay_channel",
                        "pay_status",
                        "is_integral",
                        "create_date",
                        "update_date",
                        "remarks",
                        "status",
                        "version"
                )
                .INTO_VALUES("#{goodsOrderId.id}", "#{orderNo}", "#{orderType}", "#{memberId}", "#{shopId}", "#{inviteCode}", "#{memberName}", "#{supplierId}", "#{orderStatus.code}", "#{afterStatus}",
                        "#{goodsCount}",
                        "#{goodsAmountTotal}",
                        "#{logisticsFee}",
                        "#{couponAmount}",
                        "#{orderAmountTotal}",
                        "#{goodsOrderAddress.goodsOrderAddressId.id}",
                        "#{payChannel.code}",
                        "#{payStatus.code}",
                        "#{isIntegral}",
                        "#{createDate}",
                        "#{updateDate}",
                        "#{remarks}",
                        "#{status.code}",
                        "#{version}"
                )
                .toString();
    }

    /**
     * 查询sql
     *
     * @param query
     * @return
     */
    public String queryGoodsOrderSql(GoodsOrderQry query) {
        return new SQL() {{
            SELECT("*");
            FROM("goods_order a");
            LEFT_OUTER_JOIN("goods_order_address c on a.id = c.order_id");
            LEFT_OUTER_JOIN("goods_order_detail b on a.id = b.order_id");
            WHERE("1=1");
            if (query.getMemberId() != null) {
                WHERE("a.member_id=" + query.getMemberId());
            }
            //会员名称
            if (StringUtils.isNotBlank(query.getMemberName())) {
                WHERE("a.member_name like '%" + query.getMemberName() + "%'");
            }
            //订单号
            if (query.getShopId() != null) {
                WHERE("a.shop_id =#{shopId}");
            }
            //订单号
            if (StringUtils.isNotBlank(query.getOrderNo())) {
                WHERE("a.order_no like '%" + query.getOrderNo() + "%'");
            }
            //订单状态
            if (query.getOrderStatus() != null) {
                WHERE("a.order_status=" + query.getOrderStatus());
            }
            //订单类型
            if (query.getOrderType() != null) {
                WHERE("a.order_type =" + query.getOrderType());
            }
            //创建时间
            if (query.getCreateStarTime() != null && query.getCreateEndTime() != null) {
                WHERE("a.create_date between #{createStarTime} and #{createEndTime}");
            }
            //支付时间
            if (query.getPayStarTime() != null && query.getPayEndTime() != null) {
                WHERE("a.pay_time between #{payStarTime} and #{payEndTime}");
            }
            //完成时间
            if (query.getFinshStarTime() != null && query.getFinshEndTime() != null) {
                WHERE("a.finsh_date between #{finshStarTime} and #{finshEndTime}");
            }

            //商品名称
            if (StringUtils.isNotBlank(query.getGoodsName())) {
                WHERE("b.goods_name like '%" + query.getGoodsName() + "%'");
            }
            //收货人姓名
            if (StringUtils.isNotBlank(query.getUserName())) {
                WHERE("c.user_name like '%" + query.getUserName() + "%'");
            }
            //收货人手机
            if (StringUtils.isNotBlank(query.getUserPhone())) {
                WHERE("c.user_phone like '%" + query.getUserPhone() + "%'");
            }
            //供应商
            if (query.getSupplierId() != null) {
                WHERE("b.supplier_id =#{supplierId}");
            }
            WHERE("a.status != 4");
            ORDER_BY("a.create_date desc");
        }}.toString();
    }

    /**
     * 查询寄样信息sql
     *
     * @param query
     * @return
     */
    public String queryGoodsSimpleOrderSql(GoodsSimpleQry query) {
        return new SQL() {{
            SELECT("*");
            FROM("goods_order_sample ");
            WHERE("1=1");
            if (query.getSampleStatus() != null) {
                WHERE("sample_status =#{sampleStatus}");
            }
            if (StringUtils.isNotBlank(query.getSampleNo())) {
                WHERE("sample_no like '%" + query.getSampleNo() + "%'");
            }
            //店铺
            if (query.getShopId() != null) {
                WHERE("shop_id =#{shopId}");
            }
            if (query.getMemberId() != null) {
                WHERE("member_id =#{memberId}");
            }
            //收货人姓名
            if (StringUtils.isNotBlank(query.getUserName())) {
                WHERE("user_name like '%" + query.getUserName() + "%'");
            }
            //收货人手机
            if (StringUtils.isNotBlank(query.getUserPhone())) {
                WHERE("user_phone like '%" + query.getUserPhone() + "%'");
            }

            WHERE("status != 4");
            ORDER_BY("create_date desc");
        }}.toString();
    }


    /**
     * 更新sql
     *
     * @param goodsOrder
     * @return
     */
    public String updateGoodsOrderSql(GoodsOrder goodsOrder) {
        return new SQL() {{
            UPDATE("goods_order");
            if (null != goodsOrder.getOrderStatus()) {
                SET("order_status = #{orderStatus.code}");
            }
            //订单状态
            if (null != goodsOrder.getStatus()) {
                SET("status = #{status.code}");
            }
            //是否自动取消
            if (StringUtils.isNotBlank(goodsOrder.getAutoCancel())) {
                SET("auto_cancel = #{autoCancel}");
            }
            //第三方支付流水
            if (StringUtils.isNotBlank(goodsOrder.getEscrowTradeNo())) {
                SET("escrow_trade_no = #{escrowTradeNo}");
            }
            //支付时间
            if (null != goodsOrder.getPayTime()) {
                SET("pay_time = #{payTime}");
            }
            if (null != goodsOrder.getPayStatus()) {
                SET("pay_status = #{payStatus.code}");
            }
            //更新时间
            if (null != goodsOrder.getUpdateDate()) {
                SET("update_date = #{updateDate}");
            }
            //完成时间
            if (null != goodsOrder.getPayChannel()) {
                SET("pay_channel = #{payChannel.code}");
            }
            //完成时间
            if (null != goodsOrder.getFinshDate()) {
                SET("finsh_date = #{finshDate}");
            }
            //更新订单备注
            if (StringUtils.isNotBlank(goodsOrder.getSystemRemarks())) {
                SET("system_remarks = #{systemRemarks}");
            }
            WHERE("id = " + goodsOrder.getGoodsOrderId().getId());
        }}.toString();
    }

    /**
     * 根据供应商id查询订单信息
     *
     * @param qry
     * @return
     */
    public String countOrder(ManagerOrderCountQry qry) {
        return new SQL() {{
            SELECT(" * "
            );
            FROM("goods_order");
            WHERE("shop_id=" + qry.getShopId());
            WHERE("pay_status=0");
            if (2 == qry.getQryType()) {
                WHERE(" TO_DAYS( NOW( ) ) - TO_DAYS( create_date ) = 1 ");
            } else if (3 == qry.getQryType()) {
                WHERE(" DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_date) ");
            } else if (4 == qry.getQryType()) {
                WHERE(" create_date between #{startTime} and #{endTime}");
            } else {
                WHERE(" to_days( create_date ) = to_days(now()) ");
            }
        }}.toString();
    }


    /**
     * 保存收货地址sql
     *
     * @param goodsOrderAddress
     * @return
     */
    public String createGoodsOrderAddressSql(GoodsOrderAddress goodsOrderAddress) {
        log.info("进入创建订单收货地址sql");
        return new SQL()
                .INSERT_INTO("goods_order_address")
                .INTO_COLUMNS("id", "order_id", "user_name", "user_address", "user_phone", "create_date", "update_date", "remarks", "status", "version")
                .INTO_VALUES("#{goodsOrderAddressId.id}", "#{goodsOrderId.id}", "#{userName}", "#{userAddress}", "#{userPhone}", "#{createDate}", "#{updateDate}", "#{remarks}", "#{status.code}", "#{version}")
                .toString();
    }


    /**
     * 保存订单产品信息
     *
     * @param goodsOrderDetail
     * @return
     */
    public String createGoodsOrderDetailSql(GoodsOrderDetail goodsOrderDetail) {
        log.info("进入创建订单详情sql");
        return new SQL()
                .INSERT_INTO("goods_order_detail")
                .INTO_COLUMNS("id", "order_id", "goods_spu_id", "supplier_id", "supplier_name", "goods_name", "sku_name", "goods_image",
                        "goods_price", "cost_price", "logistics_fee", "goods_sku_id", "discount_rate", "discount_amount", "number",
                        "subtotal", "is_goods_exists", "create_date", "update_date", "remarks", "status", "version",
                        "is_sendcode", "reserve_address", "auto_send_code")
                .INTO_VALUES("#{goodsOrderDetailId.id}", "#{goodsOrderId.id}", "#{goodsSpuId}", "#{supplierId}",
                        "#{supplierName}", "#{goodsName}", "#{skuName}", "#{goodsImage}", "#{goodsPrice}", "#{costPrice}", "#{logisticsFee}",
                        "#{goodsSkuId}", "#{discountRate}", "#{discountAmount}", "#{number}", "#{subtotal}", "#{isGoodsExists}",
                        "#{createDate}", "#{updateDate}", "#{remarks}", "#{status.code}", "#{version}",
                        "#{isSendcode}", "#{reserveAddress}", "#{autoSendCode}")
                .toString();
    }


    /**
     * 查询订单产品详情
     *
     * @param goodsOrderId
     * @return
     */
    public String queryGoodsOrderDetailSql(GoodsOrderId goodsOrderId) {
        return new SQL() {{
            SELECT("*");
            FROM("goods_order_detail");
            WHERE("1=1");
            if (goodsOrderId.getId() != null) {
                WHERE("order_id=" + goodsOrderId.getId());
            }
        }}.toString();
    }


    /**
     * 保存优惠券使用信息
     *
     * @param goodsOrderCoupon
     * @return
     */
    public String createGoodsOrderCouponSql(GoodsOrderCoupon goodsOrderCoupon) {
        log.info("进入创建优惠券使用信息sql");
        return new SQL()
                .INSERT_INTO("goods_order_coupon")
                .INTO_COLUMNS("id", "order_id", "coupon_receive_id", "name", "coupon_amount")
                .INTO_VALUES("#{goodsOrderCouponId.id}", "#{goodsOrderId.id}", "#{couponReceiveId}", "#{name}", "#{couponAmount}")
                .toString();
    }


    /**
     * 根据订单号查询优惠券使用信息
     *
     * @param goodsOrderCoupon 优惠券信息
     * @return
     */
    public String queryGoodsOrderCouponSql(GoodsOrderCoupon goodsOrderCoupon) {
        return new SQL() {{
            SELECT("*");
            FROM("goods_order_coupon");
            WHERE("1=1");
            if (goodsOrderCoupon.getGoodsOrderId().getId() != null) {
                WHERE("order_id=" + goodsOrderCoupon.getGoodsOrderId().getId());
            }
        }}.toString();
    }


    /**
     * 新增订单快递信息
     *
     * @param goodsOrderExpress
     * @return
     */
    public String createGoodsExpressSql(GoodsOrderExpress goodsOrderExpress) {
        log.info("进入新增订单快递信息sql");
        return new SQL()
                .INSERT_INTO("goods_express")
                .INTO_COLUMNS("id", "order_id", "express_company_name", "express_no", "express_code", "create_date")
                .INTO_VALUES("#{goodsOrderExpressId.id}", "#{goodsOrderId.id}", "#{expressCompanyName}", "#{expressNo}", "#{expressCode}", "#{createDate}")
                .toString();
    }


    public String createVerificationCodeSql(OrderVerificationCode orderVerificationCode) {
        log.info("进入创建编码sql");
        return new SQL()
                .INSERT_INTO("order_verification_code")
                .INTO_COLUMNS("id", "order_id", "order_detail_id", "verify_sn", "qr_code", "is_use", "create_date", "update_date", "remarks", "status", "version")
                .INTO_VALUES("#{orderVerificationCodeId.id}", "#{goodsOrderId.id}", "#{goodsOrderDetailId.id}", "#{verifySn}", "#{qrCode}", "#{isUse}", "#{createDate}", "#{updateDate}", "#{remarks}", "#{status.code}", "#{version}")
                .toString();
    }


    /**
     * 根据供应商id查询订单信息
     *
     * @param qry
     * @return
     */
    public String queryGoodsOrderDetailBySupplierIdSql(OrderCountQry qry) {
        return new SQL() {{
            SELECT(" o.id as id," +
                    "o.order_no as orderNo," +
                    "o.order_type as orderType," +
                    "o.member_id as memberId," +
                    "o.member_name as memberName," +
                    "o.order_status as orderStatus," +
                    "o.after_status as afterStatus," +
                    "o.pay_channel as payChannel," +
                    "o.order_settlement_time as orderSettlementTime," +
                    "od.id  as goodsOrderDetailId," +
                    "od.supplier_id as supplierId," +
                    "od.supplier_name as supplierName," +
                    "od.logistics_fee as logisticsFee," +
                    "od.goods_spu_id as goodsSpuId," +
                    "od.goods_name as goodsName," +
                    "od.goods_price as goodsPrice," +
                    "od.goods_sku_id as goodsSkuId," +
                    "od.number as number," +
                    "od.cost_price as costPrice," +
                    "od.subtotal as subtotal," +
                    "od.is_sendcode as isSendcode," +
                    "od.auto_send_code as autoSendCode"
            );
            FROM("goods_order_detail od");
            LEFT_OUTER_JOIN("goods_order o ON o.id=od.order_id");
            WHERE("1=1");
            WHERE("od.supplier_id=" + qry.getSupplierId());
            if (2 == qry.getQryType()) {
                WHERE(" TO_DAYS( NOW( ) ) - TO_DAYS( o.pay_time ) = 1 ");
            } else if (3 == qry.getQryType()) {
                WHERE(" DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(o.pay_time) ");
            } else {
                WHERE(" to_days( o.pay_time ) = to_days(now()) ");
            }
        }}.toString();
    }

}
