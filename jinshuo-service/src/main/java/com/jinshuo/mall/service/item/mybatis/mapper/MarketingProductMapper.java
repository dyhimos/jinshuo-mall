package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.marketing.MarketingProduct;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface MarketingProductMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("<script>" +
            "INSERT INTO marketing_product(id,marketing_id,spu_id,stock,price,create_date,update_date,remarks,sort) " +
            "VALUES " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.marketingProductId.id},#{item.marketingId},#{item.spuId},#{item.stock},#{item.price},#{item.createDate},#{item.updateDate},#{item.remarks},#{item.sort})" +
            "</foreach>" +
            "</script>")
    void create(List<MarketingProduct> list);

    @Results(
            id = "marketingProductResult",
            value = {
                    @Result(property = "marketingProductId.id", column = "id"),
                    @Result(property = "marketingId", column = "marketing_id"),
                    @Result(property = "spuId", column = "spu_id"),
                    @Result(property = "stock", column = "stock"),
                    @Result(property = "price", column = "price"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM marketing_product WHERE status = 1 AND id=#{marketingProductId}")
    MarketingProduct queryById(@Param("marketingProductId") Long marketingProductId);

    @ResultMap("marketingProductResult")
    @Select("SELECT * FROM marketing_product WHERE status=1 AND marketing_id=#{marketingId} ORDER BY sort DESC ")
    List<MarketingProduct> findAll(@Param("marketingId") Long marketingId);

    @ResultMap("marketingProductResult")
    @Select("SELECT * FROM marketing_product WHERE status=1 AND spu_id=#{spuId} ORDER BY sort DESC ")
    List<MarketingProduct> queryBySpuId(@Param("spuId") Long spuId);

    @Update("UPDATE  marketing_product SET status=4 " +
            " WHERE id = #{marketingProductId}")
    int delete(@Param("marketingProductId") Long marketingProductId);


    @Delete("DELETE FROM marketing_product WHERE marketing_id=#{marketingId} ")
    int deleteByMarketingId(@Param("marketingId") Long marketingId);
}
