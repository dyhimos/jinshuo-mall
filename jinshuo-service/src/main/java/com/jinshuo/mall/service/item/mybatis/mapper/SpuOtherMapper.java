package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.spuOther.SpuOther;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SpuOtherMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spu_other(id,spu_id,is_show_sell,courier_fee,up_time,down_time,create_date,update_date,remarks," +
            "buy_start_date,buy_end_date,activity_address,activity_date,is_scare_buy,activity_start_date,activity_end_date )" +
            "VALUES(#{spuOtherId.id},#{spuId.id},#{isShowSell},#{courierFee},#{upTime},#{downTime},#{createDate},#{updateDate},#{remarks}," +
            "#{buyStartDate},#{buyEndDate},#{activityAddress},#{activityDate},#{isScareBuy},#{activityStartDate},#{activityEndDate}" +
            ")")
    void create(SpuOther spuOther);

    @Results(
            id = "goodsspuotherResult",
            value = {
                    @Result(property = "spuOtherId.id", column = "id"),
                    @Result(property = "spuId.id", column = "spu_id"),
                    @Result(property = "isShowSell", column = "is_show_sell"),
                    @Result(property = "courierFee", column = "courier_fee"),
                    @Result(property = "upTime", column = "up_time"),
                    @Result(property = "downTime", column = "down_time"),
                    @Result(property = "buyStartDate", column = "buy_start_date"),
                    @Result(property = "buyEndDate", column = "buy_end_date"),
                    @Result(property = "activityAddress", column = "activity_address"),
                    @Result(property = "activityDate", column = "activity_date"),
                    @Result(property = "isScareBuy", column = "is_scare_buy"),
                    @Result(property = "activityStartDate", column = "activity_start_date"),
                    @Result(property = "activityEndDate", column = "activity_end_date"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spu_other WHERE status = 1 AND id=#{spuOtherId} LIMIT 1")
    SpuOther queryById(@Param("spuOtherId") Long spuOtherId);

    @ResultMap("goodsspuotherResult")
    @Select("SELECT * FROM goods_spu_other WHERE status = 1 AND spu_id=#{spuId} LIMIT 1 ")
    SpuOther queryBySpuId(@Param("spuId") Long spuId);

    @Update("UPDATE  goods_spu_other SET " +
            "is_show_sell=#{isShowSell}," +
            "courier_fee=#{courierFee} ," +
            "up_time=#{upTime}," +
            "down_time=#{downTime}," +
            "buy_start_date=#{buyStartDate}," +
            "buy_end_date=#{buyEndDate}," +
            "activity_address=#{activityAddress}," +
            "activity_date=#{activityDate},is_scare_buy=#{isScareBuy}," +
            "activity_start_date=#{activityStartDate},activity_end_date=#{activityEndDate}," +
            "update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{spuOtherId.id}")
    void update(SpuOther spuOther);

    @ResultMap("goodsspuotherResult")
    @Select("SELECT * FROM goods_spu_other WHERE status='1'")
    List<SpuOther> findAll();

}
