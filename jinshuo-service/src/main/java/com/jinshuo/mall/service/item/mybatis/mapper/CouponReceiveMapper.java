package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.service.item.application.qry.CouponReceiveQry;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CouponReceiveMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO coupon_receive(" +
            "id,coupon_id,mem_id,coupon_code,receive_time," +
            "use_time,use_status," +
            "update_date,remarks,create_date" +
            ") " +
            "VALUES(#{couponReceiveId.id},#{couponId},#{memId},#{couponCode},#{receiveTime}," +
            "#{useTime},#{useStatus}," +
            "#{updateDate},#{remarks},#{createDate})")
    int create(CouponReceive couponReceive);

    @Results(
            id = "couponreceiveResult",
            value = {
                    @Result(property = "couponReceiveId.id", column = "id"),
                    @Result(property = "couponId", column = "coupon_id"),
                    @Result(property = "memId", column = "mem_id"),
                    @Result(property = "couponCode", column = "coupon_code"),
                    @Result(property = "receiveTime", column = "receive_time"),
                    @Result(property = "useTime", column = "use_time"),
                    @Result(property = "useStatus", column = "use_status"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM coupon_receive WHERE status = 1 AND id=#{couponReceiveId}")
    CouponReceive queryById(@Param("couponReceiveId") Long couponReceiveId);

    @Update("UPDATE  coupon_receive SET coupon_id=#{couponId},memId=#{mem_id} ," +
            "coupon_code=#{couponCode},receive_time=#{receiveTime},use_time=#{useTime},use_status=#{useStatus}," +
            "update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{couponReceiveId.id}")
    int update(CouponReceive couponReceive);

    @ResultMap("couponreceiveResult")
    @Select("<script>" +
            "SELECT * FROM coupon_receive WHERE status=1 " +
            "<if test='memId != null'> " +
            "AND mem_id = #{memId}" +
            "</if>" +
            "<if test='useStatus != null'> " +
            "AND use_status = #{useStatus}" +
            "</if>" +
            "</script>")
    List<CouponReceive> findAll(CouponReceiveQry qry);

    @Update("UPDATE  coupon_receive SET status=4 " +
            " WHERE id = #{couponReceiveId}")
    int delete(@Param("couponReceiveId") Long couponReceiveId);

    @ResultMap("couponreceiveResult")
    @Select("<script>" +
            "SELECT * FROM coupon_receive WHERE status=1 " +
            "<if test='memId != null'> " +
            "AND mem_id = #{memId}" +
            "</if>" +
            "<if test='useStatus != null'> " +
            "AND use_status = #{useStatus}" +
            "</if>" +
            "<if test='couponId != null'> " +
            "AND coupon_id = #{couponId}" +
            "</if>" +
            "</script>")
    List<CouponReceive> queryByExmple(CouponReceive couponReceive);

    @Update("UPDATE  coupon_receive SET use_status=#{useStatus} " +
            " WHERE id = #{couponReceiveId}")
    int invalidCoupon(@Param("couponReceiveId") Long couponReceiveId, @Param("useStatus") Integer useStatus);

    @ResultMap("couponreceiveResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "queryMyCouponSql")
    public List<CouponReceive> queryMyCouponBySpu(CouponReceiveQry qry);

    @Update("UPDATE  coupon_receive SET use_status=#{useStatus},use_time=#{useTime} " +
            " WHERE id = #{couponReceiveId}")
    int invalidCouponWithTime(@Param("couponReceiveId") Long couponReceiveId, @Param("useStatus") Integer useStatus, @Param("useTime") Date useTime);
}
