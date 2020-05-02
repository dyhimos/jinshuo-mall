package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.couponlogs.CouponLogs;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CouponLogsMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO coupon_logs(" +
            "id,coupon_receive_id,order_id,order_original_amount,coupon_amount," +
            "order_final_amount,use_time,mem_id," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{couponLogsId.id},#{couponReceiveId},#{orderId},#{orderOriginalAmount},#{couponAmount}," +
            "#{orderFinalAmount},#{useTime},#{memId}," +
            "#{createDate},#{updateDate},#{remarks})")
    void create(CouponLogs couponLogs);

    @Results(
            id = "couponlogsResult",
            value = {
                    @Result(property = "couponLogsId.id", column = "id"),
                    @Result(property = "couponReceiveId", column = "coupon_receive_id"),
                    @Result(property = "orderId", column = "order_id"),
                    @Result(property = "orderOriginalAmount", column = "order_original_amount"),
                    @Result(property = "couponAmount", column = "coupon_amount"),
                    @Result(property = "orderFinalAmount", column = "order_final_amount"),
                    @Result(property = "useTime", column = "use_time"),
                    @Result(property = "memId", column = "mem_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM coupon_logs WHERE status = '1' AND id=#{tagId}")
    CouponLogs queryById(@Param("tagId") Long couponLogsId);

    @Update("UPDATE  coupon_logs SET name=#{name},goods_count=#{goodsCount} ," +
            "sort=#{sort},create_date=#{createDate},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{couponLogsId.id}")
    int update(CouponLogs couponLogs);

    @ResultMap("couponlogsResult")
    @Select("SELECT * FROM coupon_logs WHERE status=1 ")
    List<CouponLogs> findAll();

    @Update("UPDATE  coupon_logs SET status=4 " +
            " WHERE id = #{couponLogsId}")
    int delete(@Param("couponLogsId") Long couponLogsId);

    @ResultMap("couponlogsResult")
    @Select("SELECT " +
            "cl.id, " +
            "cl.coupon_receive_id, " +
            "cl.order_id, " +
            "cl.order_original_amount, " +
            "cl.coupon_amount, " +
            "cl.order_final_amount, " +
            "cl.use_time, " +
            "cl.mem_id, " +
            "cl.status, " +
            "cl.version, " +
            "cl.remarks, " +
            "cl.create_date, " +
            "cl.update_date " +
            "FROM coupon_logs cl, coupon_receive cr WHERE " +
            "cl.status=1 AND cr.status=1 AND cr.mem_id=#{memId} AND cl.coupon_receive_id=cr.id AND cr.coupon_id=#{couponId} ORDER BY cr.create_date DESC ")
    List<CouponLogs> queryMyLogsByCouponId(@Param("memId") Long memId, @Param("couponId") Long couponId);
}
