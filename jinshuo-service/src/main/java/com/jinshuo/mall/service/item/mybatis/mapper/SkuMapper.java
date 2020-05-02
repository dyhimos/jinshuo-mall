package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.sku.Sku;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SkuMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_sku" +
            "(id,spu_id,name,picture_url,price,market_price,cost_price,stock,sales_quantity,sku_code,bar_code," +
            "create_date,update_date,remarks)" +
            "VALUES(#{skuId.id},#{spuId.id},#{name},#{pictureUrl},#{price},#{marketPrice},#{costPrice},#{stock},#{salesQuantity}," +
            "#{skuCode},#{barCode}," +
            "#{createDate},#{updateDate},#{remarks})")
    void create(Sku sku);

    @Update("update goods_sku set name=#{name},picture_url=#{pictureUrl},price=#{price},market_price=#{marketPrice}," +
            "cost_price=#{costPrice},stock=#{stock},sales_quantity=#{salesQuantity},sku_code=#{skuCode},bar_code=#{barCode},update_date=now()" +
            " where id = #{skuId.id}")
    int update(Sku sku);

    @Update("update goods_sku set status='4' where spu_id = #{spuId}")
    int deleteBySpuId(@Param("spuId") Long spuId);

    @Results(
            id = "skuResult",
            value = {
                    @Result(property = "skuId.id", column = "id"),
                    @Result(property = "spuId.id", column = "spu_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "pictureUrl", column = "picture_url"),
                    @Result(property = "price", column = "price"),
                    @Result(property = "marketPrice", column = "market_price"),
                    @Result(property = "costPrice", column = "cost_price"),
                    @Result(property = "stock", column = "stock"),
                    @Result(property = "salesQuantity", column = "sales_quantity"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_sku  where spu_id = #{spuId} AND status=1 ")
    List<Sku> queryBySpuId(@Param("spuId") Long spuId);


    @ResultMap("skuResult")
    @Select("SELECT * FROM goods_sku WHERE id=#{skuId} AND status=1 ORDER BY update_date DESC")
    Sku queryBySkuId(@Param("skuId") Long skuId);


    @Update("update goods_sku set stock=#{stock},sales_quantity=#{salesQuantity} where id = #{skuId.id}")
    int updateStock(Sku sku);

    @Update("update goods_sku set status=4 where id = #{skuId}")
    int deleteById(@Param("skuId") Long skuId);

}
