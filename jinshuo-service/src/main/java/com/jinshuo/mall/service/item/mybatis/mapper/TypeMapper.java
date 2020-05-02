package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.type.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface TypeMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_type(id,name,goods_count,sort,create_date,update_date,remarks) " +
            "VALUES(#{typeId.id},#{name},#{goodsCount},#{sort},#{createDate},#{updateDate},#{remarks})")
    void create(Type tag);

    @Results(
            id = "goodstypeResult",
            value = {
                    @Result(property = "typeId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "goodsCount", column = "goods_count"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_type WHERE status = '1' AND id=#{typeId}")
    Type queryById(@Param("tagId") Long typeId);

    @Update("UPDATE  goods_type SET name=#{name},goods_count=#{goodsCount} ," +
            "sort=#{sort},create_date=#{createDate},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{typeId.id}")
    int update(Type tag);

    @ResultMap("goodstypeResult")
    @Select("SELECT * FROM goods_type WHERE status=1 ORDER BY SORT")
    List<Type> findAll();

    @Update("UPDATE  goods_type SET status=4 " +
            " WHERE id = #{typeId}")
    int delete(@Param("typeId") Long typeId);

}
