package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.ad.Advertisement;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface AdvertisementMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO cms_ad(id,ad_position_id,titile,image,url,sort,is_enabled,area_names," +
            "create_date,update_date,remarks) " +
            "VALUES(#{advertisementId.id},#{adPositionId.id},#{titile},#{image},#{url},#{sort},#{isEnabled},#{areaNames}," +
            "#{createDate},#{updateDate},#{remarks})")
    void create(Advertisement advertisement);

    @Results(
            id = "adResult",
            value = {
                    @Result(property = "advertisementId.id", column = "id"),
                    @Result(property = "adPositionId.id", column = "ad_position_id"),
                    @Result(property = "titile", column = "titile"),
                    @Result(property = "image", column = "image"),
                    @Result(property = "url", column = "url"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "isEnabled", column = "is_enabled"),
                    @Result(property = "areaNames", column = "area_names"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM cms_ad WHERE status = 1 AND id=#{advertisementId}")
    Advertisement queryById(@Param("advertisementId") Long advertisementId);

    @Update("UPDATE  cms_ad SET ad_position_id=#{adPositionId.id},titile=#{titile},image=#{image},url=#{url},sort=#{sort},is_enabled=#{isEnabled}," +
            "update_date =#{updateDate},remarks =#{remarks},area_names =#{areaNames}" +
            " WHERE id = #{advertisementId.id}")
    void update(Advertisement advertisement);

    @ResultMap("adResult")
    @Select("SELECT * FROM cms_ad WHERE status=1 ORDER BY SORT DESC")
    List<Advertisement> findAll();

    @Update("UPDATE  cms_ad SET status=4 " +
            " WHERE id = #{advertisementId}")
    int delete(@Param("advertisementId") Long advertisementId);

    @ResultMap("adResult")
    @Select("SELECT * FROM cms_ad WHERE status=1 AND ad_position_id=#{adPositionId} ORDER BY sort DESC ")
    List<Advertisement> queryByPositionId(@Param("adPositionId") Long adPositionId);


    @ResultMap("adResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "queryAd")
    List<Advertisement> queryByUsedPositionId(Advertisement advertisement);

}
