package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.tag.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface TagMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_tag(id,name,goods_count,sort,shop_id,create_date,update_date,remarks) " +
            "VALUES(#{tagId.id},#{name},#{goodsCount},#{sort},#{shopId},#{createDate},#{updateDate},#{remarks})")
    void create(Tag tag);

    @Results(
            id = "goodstagResult",
            value = {
                    @Result(property = "tagId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "goodsCount", column = "goods_count"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_tag WHERE status = '1' AND id=#{tagId}")
    Tag queryById(@Param("tagId") Long tagId);

    @Update("UPDATE  goods_tag SET name=#{name},goods_count=#{goodsCount} ," +
            "sort=#{sort},shop_id=#{shopId},create_date=#{createDate},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{tagId.id}")
    int update(Tag tag);

    @ResultMap("goodstagResult")
    @Select("SELECT * FROM goods_tag WHERE status=1 ORDER BY SORT")
    List<Tag> findAll();

    @Update("UPDATE  goods_tag SET status=4 " +
            " WHERE id = #{tagId}")
    int delete(@Param("tagId") Long tagId);

}
