package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.feature.Feature;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface FeatureMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_feature(id,name,goods_count,sort,shop_id,merchant_id,create_date,update_date,remarks) " +
            "VALUES(#{featureId.id},#{name},#{goodsCount},#{sort},#{shopId},#{merchantId},#{createDate},#{updateDate},#{remarks})")
    int create(Feature feature);

    @Results(
            id = "featureResult",
            value = {
                    @Result(property = "featureId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "goodsCount", column = "goods_count"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "merchantId", column = "merchant_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_feature WHERE status = '1' AND id=#{featureId}")
    Feature queryById(@Param("featureId") Long featureId);

    @Update("UPDATE  goods_feature SET name=#{name},goods_count=#{goodsCount} ," +
            "sort=#{sort},shop_id=#{shopId},merchant_id=#{merchantId},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{featureId.id}")
    int update(Feature feature);

    @ResultMap("featureResult")
    @Select("SELECT * FROM goods_feature WHERE status=1 AND shop_id=#{shopId} ORDER BY SORT DESC")
    List<Feature> findAll(@Param("shopId") Long shopId);

    @Update("UPDATE  goods_feature SET status=4 " +
            " WHERE id = #{featureId}")
    int delete(@Param("featureId") Long featureId);

}
