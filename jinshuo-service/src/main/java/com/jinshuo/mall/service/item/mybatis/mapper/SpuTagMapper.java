package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.spu.sputag.SpuTag;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SpuTagMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spu_tag(id,spu_id,tag_id,create_date,update_date,remarks) " +
            "VALUES(#{spuTagId.id},#{spuId.id},#{tagId.id},#{createDate},#{updateDate},#{remarks})")
    void create(SpuTag spuTag);

    @Results(
            id = "goodstagResult",
            value = {
                    @Result(property = "spuTagId.id", column = "id"),
                    @Result(property = "spuId.id", column = "spu_id"),
                    @Result(property = "tagId.id", column = "tag_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spu_tag WHERE status = 1 AND id=#{tagId}")
    SpuTag queryById(@Param("tagId") Long tagId);

    @ResultMap("goodstagResult")
    @Select("SELECT * FROM goods_spu_tag WHERE status = 1 AND spu_id=#{spuId} ")
    List<SpuTag> queryBySpuId(@Param("spuId") Long spuId);

    @Update("UPDATE  goods_spu_tag SET status=4" +
            " WHERE spu_id = #{spuId}")
    int deleteBySpuId(@Param("spuId") Long spuId);

    @Update("UPDATE  goods_spu_tag SET status=4" +
            " WHERE id = #{tagId}")
    int deleteById(@Param("tagId") Long tagId);

}
