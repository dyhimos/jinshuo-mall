package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.sku.SkuOption;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SkuOptionMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_sku_attribute_and_option" +
            "(id,sku_id,spec_id,spec_option_id,sort,create_date,update_date,remarks)" +
            "VALUES(#{skuOptionId.id},#{skuId.id},#{specId.id},#{specOptionId.id},#{sort}," +
            "#{createDate},#{updateDate},#{remarks})")
    void create(SkuOption skuOption);

    @Update("update goods_sku_attribute_and_option set status='4' where sku_id = #{skuId}")
    int deleteBySkuId(@Param("skuId") Long skuId);

    @Results(
            id = "skuResult",
            value = {
                    @Result(property = "skuOptionId.id", column = "id"),
                    @Result(property = "skuId.id", column = "sku_id"),
                    @Result(property = "specId.id", column = "spec_id"),
                    @Result(property = "specOptionId.id", column = "spec_option_id"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_sku_attribute_and_option  where sku_id = #{skuId} AND status='1' ORDER BY sort  " )
    List<SkuOption> queryBySkuId(@Param("skuId") Long skuId);
}
