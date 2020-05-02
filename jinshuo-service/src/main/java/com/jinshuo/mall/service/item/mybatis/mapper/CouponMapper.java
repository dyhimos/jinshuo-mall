package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.service.item.application.qry.CouponQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CouponMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO coupon(" +
            "id,name,type,coupon_amount,amount," +
            "max_limit,received_quantity,checkquantity,is_condition,scope_type," +
            "validit_type,validity_start_date,vaildity_end_date,vaildity_days,gain_count," +
            "gain_method,coupon_desc,coupon_status,audit_status,update_date," +
            "receive_start_time,receive_end_time," +
            "remarks,version,create_date," +
            "sort,is_show,shop_id,for_people ) " +
            "VALUES(#{couponId.id},#{name},#{type},#{couponAmount},#{amount}," +
            "#{maxLimit},#{receivedQuantity},#{checkquantity},#{isCondition},#{scopeType}," +
            "#{validitType},#{validityStartDate},#{vaildityEndDate},#{vaildityDays},#{gainCount}," +
            "#{gainMethod},#{couponDesc},#{couponStatus},#{auditStatus},#{updateDate}," +
            "#{receiveStartTime},#{receiveEndTime}," +
            "#{remarks},#{version},#{createDate}," +
            "#{sort},#{isShow},#{shopId},#{forPeople})")
    void create(Coupon coupon);

    @Results(
            id = "couponResult",
            value = {
                    @Result(property = "couponId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "couponAmount", column = "coupon_amount"),
                    @Result(property = "amount", column = "amount"),
                    @Result(property = "maxLimit", column = "max_limit"),
                    @Result(property = "receivedQuantity", column = "received_quantity"),
                    @Result(property = "checkquantity", column = "checkquantity"),
                    @Result(property = "isCondition", column = "is_condition"),
                    @Result(property = "scopeType", column = "scope_type"),
                    @Result(property = "receiveStartTime", column = "receive_start_time"),
                    @Result(property = "receiveEndTime", column = "receive_end_time"),
                    @Result(property = "validitType", column = "validit_type"),
                    @Result(property = "validityStartDate", column = "validity_start_date"),
                    @Result(property = "vaildityEndDate", column = "vaildity_end_date"),
                    @Result(property = "vaildityDays", column = "vaildity_days"),
                    @Result(property = "gainCount", column = "gain_count"),
                    @Result(property = "gainMethod", column = "gain_method"),
                    @Result(property = "couponDesc", column = "coupon_desc"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "isShow", column = "is_show"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "forPeople", column = "for_people"),
                    @Result(property = "couponStatus", column = "coupon_status"),
                    @Result(property = "auditStatus", column = "audit_status"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "status.code", column = "status"),
            }
    )
    @Select("SELECT * FROM coupon WHERE  id=#{couponId} LIMIT 1 ")
    Coupon queryById(@Param("couponId") Long couponId);

    @Update("UPDATE  coupon SET " +
            "name=#{name}," +
            "type=#{type}," +
            "coupon_amount=#{couponAmount}," +
            "amount=#{amount}," +
            "max_limit=#{maxLimit}," +
            "is_condition=#{isCondition}," +
            "scope_type=#{scopeType}," +
            "validit_type=#{validitType}," +
            "validity_start_date=#{validityStartDate}," +
            "vaildity_end_date=#{vaildityEndDate}," +
            "vaildity_days=#{vaildityDays}," +
            "gain_count=#{gainCount}," +
            "gain_method=#{gainMethod}," +
            "coupon_desc=#{couponDesc}," +
            "sort=#{sort}," +
            "is_show=#{isShow}," +
            "shop_id=#{shopId}," +
            "for_people=#{forPeople}," +
            "coupon_status=#{couponStatus}," +
            "audit_status=#{auditStatus}," +
            "receive_start_time=#{receiveStartTime}," +
            "receive_end_time=#{receiveEndTime}," +
            "remarks=#{remarks}," +
            "version=#{version}," +
            "update_date=#{updateDate} " +
            " WHERE id = #{couponId.id}")
    int update(Coupon coupon);

    @ResultMap("couponResult")
    @Select("SELECT * FROM coupon WHERE status=1 ORDER BY sort DESC,update_date DESC")
    List<Coupon> findAll(CouponQry qry);

    @Update("UPDATE  coupon SET status=4 " +
            " WHERE id = #{couponId}")
    int delete(@Param("couponId") Long couponId);

    @ResultMap("couponResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "queryCouponSql")
    List<Coupon> getByDynamic(CouponQry qry);

    @ResultMap("couponResult")
    @Select("SELECT * FROM coupon WHERE  id=#{couponId} LIMIT 1 ")
    Coupon getByIdAll(@Param("couponId") Long couponId);

    @Update("UPDATE  coupon SET checkquantity=#{checkquantity} " +
            " WHERE id = #{couponId.id}")
    int updateCheckquantity(Coupon qry);

    @ResultMap("couponResult")
    @Select("<script>" +
            "SELECT * FROM coupon WHERE status=1 AND shop_id=#{shopId}" +
            "<if test='forPeople != null'> " +
            "AND for_people = #{forPeople}" +
            "</if>" +
            " ORDER BY sort DESC,update_date DESC" +
            "</script>")
    List<Coupon> queryByExmple(Coupon coupon);
}
