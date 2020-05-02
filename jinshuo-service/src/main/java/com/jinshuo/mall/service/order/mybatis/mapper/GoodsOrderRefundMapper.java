package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.product.orderrefund.GoodsOrderRefund;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/23.
 */
@Mapper
public interface GoodsOrderRefundMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into goods_order_refund (id, member_id,order_id, order_detail_id, " +
            "refund_status, sku_id, count, " +
            "amount, refund_reason, refund_remarks, " +
            "express_company_name, express_no,  " +
            " create_date, update_date, " +
            "remarks)" +
            "values (#{goodsOrderRefundId.id},#{memberId}, #{orderId}, #{orderDetailId}, " +
            "#{refundStatus}, #{skuId}, #{count}, " +
            "#{amount}, #{refundReason}, #{refundRemarks}, " +
            "#{expressCompanyName}, #{expressNo},  " +
            " #{createDate}, #{updateDate}, " +
            "#{remarks})")
    int create(GoodsOrderRefund goodsOrderRefund);


    @Results(
            id = "goodsOrderRefundResult",
            value = {
                    @Result(column = "id", property = "goodsOrderRefundId.id"),
                    @Result(column = "member_id", property = "memberId"),
                    @Result(column = "order_id", property = "orderId"),
                    @Result(column = "order_detail_id", property = "orderDetailId"),
                    @Result(column = "refund_status", property = "refundStatus"),
                    @Result(column = "sku_id", property = "skuId"),
                    @Result(column = "count", property = "count"),
                    @Result(column = "amount", property = "amount"),
                    @Result(column = "refund_reason", property = "refundReason"),
                    @Result(column = "refund_remarks", property = "refundRemarks"),
                    @Result(column = "express_company_name", property = "expressCompanyName"),
                    @Result(column = "express_no", property = "expressNo"),
                    @Result(column = "refund_time", property = "refundTime"),
                    @Result(column = "refund_documents", property = "refundDocuments"),
                    @Result(column = "review_reason", property = "reviewReason"),
                    @Result(column = "review_time", property = "reviewTime"),
                    @Result(column = "create_date", property = "createDate"),
                    @Result(column = "update_date", property = "updateDate"),
                    @Result(column = "version", property = "version"),
                    @Result(column = "status", property = "status.code"),
                    @Result(column = "remarks", property = "remarks")
            }
    )
    @Select("select * from goods_order_refund where status=1 AND id=#{id}")
    GoodsOrderRefund queryById(@Param("id") Long id);


    @ResultMap("goodsOrderRefundResult")
    @Select("select * from goods_order_refund where status=1 AND member_id=#{memberId}")
    List<GoodsOrderRefund> queryMyOrder(@Param("memberId") Long memberId);

    @Update("UPDATE  goods_order_refund SET refund_status=#{refundStatus},review_reason=#{reviewReason},review_time=#{reviewTime}" +
            " WHERE id = #{goodsOrderRefundId.id} ")
    int review(GoodsOrderRefund goodsOrderRefund);

    @Update("UPDATE  goods_order_refund SET refund_documents=#{refundDocuments},refund_time=#{refundTime}" +
            " WHERE id = #{goodsOrderRefundId.id} ")
    int refund(GoodsOrderRefund goodsOrderRefund);

}
