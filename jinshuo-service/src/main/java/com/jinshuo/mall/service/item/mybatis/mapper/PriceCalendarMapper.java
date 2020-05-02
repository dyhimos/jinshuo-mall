package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.price.PriceCalendar;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface PriceCalendarMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("<script>" +
            "INSERT INTO goods_price_calendar(id,sku_id,price,quantity,date,create_date,update_date,remarks) " +
            "VALUES" +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.priceCalendarId.id},#{item.skuId},#{item.price},#{item.quantity},#{item.date},#{item.createDate},#{item.updateDate},#{item.remarks})" +
            "</foreach>" +
            "</script>")
    int create(List<PriceCalendar> list);

    @Results(
            id = "priceCalendarResult",
            value = {
                    @Result(property = "priceCalendarId.id", column = "id"),
                    @Result(property = "skuId", column = "sku_id"),
                    @Result(property = "price", column = "price"),
                    @Result(property = "date", column = "date"),
                    @Result(property = "quantity", column = "quantity"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_price_calendar WHERE status = 1 AND sku_id=#{skuId} ORDER BY date ")
    List<PriceCalendar> queryBySkuId(@Param("skuId") Long skuId);

    @Delete("DELETE FROM goods_price_calendar WHERE sku_id=#{skuId} ")
    int deleteBySkuId(@Param("skuId") Long skuId);
}
