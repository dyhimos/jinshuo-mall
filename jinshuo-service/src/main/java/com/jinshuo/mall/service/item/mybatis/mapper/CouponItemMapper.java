package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CouponItemMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO coupon_item(id,coupon_id,target_id,type,is_apply,create_date,update_date,remarks) " +
            "VALUES(#{couponItemId.id},#{couponId.id},#{targetId},#{type},#{isApply},#{createDate},#{updateDate},#{remarks})")
    void create(CouponItem couponItem);

    @Results(
            id = "couponItemResult",
            value = {
                    @Result(property = "couponItemId.id", column = "id"),
                    @Result(property = "couponId.id", column = "coupon_id"),
                    @Result(property = "targetId", column = "target_id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "isApply", column = "is_apply"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM coupon_item WHERE status = '1' AND id=#{couponItemId}")
    CouponItem queryById(@Param("couponItemId") Long couponItemId);

    @Update("UPDATE  coupon_item SET " +
            "target_id=#{targetId}," +
            "coupon_id=#{couponId.id} ," +
            "sort=#{sort}," +
            "type=#{type}," +
            "is_apply=#{isApply}," +
            "update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{couponItemId.id}")
    int update(CouponItem couponItem);

    @ResultMap("couponItemResult")
    @Select("SELECT * FROM coupon_item WHERE status=1 ORDER BY SORT")
    List<CouponItem> findAll();

    @Update("UPDATE  coupon_item SET status=4 " +
            " WHERE id = #{couponItemId}")
    int delete(@Param("couponItemId") Long couponItemId);

    @Update("UPDATE  coupon_item SET status=4 " +
            " WHERE coupon_id = #{couponId}")
    int deleteByCouponId(@Param("couponId") Long couponId);


    @ResultMap("couponItemResult")
    @Select("SELECT * FROM coupon_item WHERE status=1 AND coupon_id = #{couponId}")
    List<CouponItem> queryByCouponId(@Param("couponId") Long couponId);

    @ResultMap("couponItemResult")
    @Select("SELECT * FROM coupon_item WHERE status=1 AND target_id = #{targetId}")
    List<CouponItem> queryByTargetId(@Param("targetId") Long targetId);

    @ResultMap("couponItemResult")
    @Select("SELECT * FROM coupon_item WHERE status=1 AND target_id = #{targetId} AND coupon_id = #{couponId} LIMIT 1")
    CouponItem checkCoupon(@Param("couponId") Long couponId, @Param("targetId") Long targetId);

    @ResultMap("couponItemResult")
    @Select("<script>" +
            "SELECT * FROM coupon_item WHERE status=1 AND shop_id=#{shopId}" +
            "<if test='isApply != null'> " +
            "AND is_apply = #{isApply}" +
            "</if>" +
            "</script>")
    List<CouponItem> queryByExmple(CouponItem couponItem);

}
