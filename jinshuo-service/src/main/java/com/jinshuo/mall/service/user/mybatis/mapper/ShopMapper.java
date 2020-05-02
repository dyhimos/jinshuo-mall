package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.shop.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/9/16 20:09
 * @Created by dyh
 */
@Mapper
public interface ShopMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO shop(" +
            "id,name,merchant_id,type,shop_status," +
            "logo,introduce,customer_tel,work_type,star_time,end_time," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{shopId.id},#{name},#{merchantId},#{type},#{shopStatus}," +
            "#{logo},#{introduce},#{customerTel},#{workType},#{starTime},#{endTime}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(Shop shop);

    @Results(
            id = "shopResult",
            value = {
                    @Result(property = "shopId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "merchantId", column = "merchant_id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "shopStatus", column = "shop_status"),
                    @Result(property = "logo", column = "logo"),
                    @Result(property = "introduce", column = "introduce"),
                    @Result(property = "customerTel", column = "customer_tel"),
                    @Result(property = "workType", column = "work_type"),
                    @Result(property = "starTime", column = "star_time"),
                    @Result(property = "endTime", column = "end_time"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM shop WHERE status = 1 AND id=#{id} LIMIT 1 ")
    Shop queryById(@Param("id") Long id);

    @Update("UPDATE  shop SET status=4 " +
            " WHERE id = #{id} ")
    int delete(@Param("id") Long id);


    @ResultMap("shopResult")
    @Select("<script>" +
            "SELECT * FROM shop WHERE status=1  " +
            "<if test='merchantId != null'> " +
            "AND merchant_id = #{merchantId}" +
            "</if>" +
            "ORDER BY create_date DESC " +
            "</script>")
    List<Shop> queryList(Shop shop);

    @Update("UPDATE  shop SET name=#{name} " +
            ",logo=#{logo},introduce=#{introduce},customer_tel=#{customerTel},work_type=#{workType}" +
            ",star_time=#{starTime},end_time=#{endTime} " +
            " WHERE id = #{shopId.id} ")
    int update(Shop shop);
}
