package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.brand.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface BrandMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_brand(id,name,picture_url,item_num,sort,create_date,update_date,remarks) " +
            "VALUES(#{brandId.id},#{name},#{pictureUrl},#{itemNum},#{sort},#{createDate},#{updateDate},#{remarks})")
    void create(Brand brand);

    @Results(
            id = "goodsbrandResult",
            value = {
                    @Result(property = "brandId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "pictureUrl", column = "picture_url"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_brand WHERE status = '1' AND id=#{brandId}")
    Brand queryById(@Param("brandId") Long brandId);

    @Update("UPDATE  goods_brand SET name=#{name},item_num=#{itemNum} ,picture_url=#{pictureUrl}," +
            "sort=#{sort},create_date=#{createDate},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{brandId.id}")
    void update(Brand brand);

    @ResultMap("goodsbrandResult")
    @Select("SELECT * FROM goods_brand WHERE status='1' ORDER BY SORT")
    List<Brand> findAll();

    @Update("UPDATE  goods_brand SET status=4 " +
            " WHERE id = #{brandId}")
    int delete(@Param("brandId") Long brandId);

}
