package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.area.City;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CityMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_city(id,shop_id,area_id,area_code,area_name,area_pro_name,open_time,open_business,create_date,update_date,remarks) " +
            "VALUES(#{cityId.id},#{shopId},#{areaId},#{areaCode},#{areaName},#{areaProName},#{openTime},#{openBusiness},#{createDate},#{updateDate},#{remarks})")
    int create(City city);

    @Results(
            id = "ctiyResult",
            value = {
                    @Result(property = "cityId.id", column = "id "),
                    @Result(property = "shopId", column = "shopId"),
                    @Result(property = "areaId", column = "area_id"),
                    @Result(property = "areaCode", column = "area_code"),
                    @Result(property = "areaName", column = "area_name"),
                    @Result(property = "areaProName", column = "area_pro_name"),
                    @Result(property = "openTime", column = "open_time"),
                    @Result(property = "openBusiness", column = "open_business"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_city WHERE status = 1 AND id=#{id} ")
    City queryById(@Param("id") Long id);

    @Update("UPDATE  goods_city SET area_name=#{areaName},area_code=#{areaCode} ," +
            "area_id=#{areaId},update_date =#{updateDate},version =#{version}" +
            " WHERE id = #{cityId.id}")
    int update(City spu);

    @ResultMap("ctiyResult")
    @Select("SELECT * FROM goods_city WHERE status=1 AND shop_id=#{shopId} ORDER BY create_date desc")
    List<City> queryByShopId(@Param("shopId") Long shopId);

    @Update("update goods_city set status=4 where id=#{id}")
    int delete(@Param("id") Long id);

    @ResultMap("ctiyResult")
    @Select("SELECT * FROM goods_city WHERE status=1 AND shop_id=#{shopId}  AND area_name=#{areaName} ORDER BY create_date desc")
    List<City> queryByAreaName(@Param("shopId") Long shopId, @Param("areaName") String areaName);

    @Update("update goods_city set status=4 where shop_id=#{shopId}")
    int deleteByShopId(@Param("shopId") Long shopId);
}
