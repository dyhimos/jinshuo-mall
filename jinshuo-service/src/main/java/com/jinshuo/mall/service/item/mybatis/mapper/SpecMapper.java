package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.spec.Spec;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SpecMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spec(id,shop_id,name,category_id,sort,create_date,update_date,remarks) " +
            "VALUES(#{specId.id},#{shopId},#{name},#{categoryId.id},#{sort},#{createDate},#{updateDate},#{remarks})")
    void create(Spec spu);

    @Results(
            id = "goodspec",
            value = {
                    @Result(property = "specId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "categoryId.id", column = "category_id"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spec WHERE status = 1 AND id=#{id}")
    Spec findById(@Param("id") Long id);

    @Update("UPDATE  goods_spec SET name=#{name},category_id=#{categoryId.id} ," +
            "sort=#{sort},create_date=#{createDate},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{specId.id}")
    void update(Spec spu);

    @ResultMap("goodspec")
    @Select("SELECT * FROM goods_spec WHERE status=1 AND shop_id=#{shopId} ORDER BY sort DESC ")
    List<Spec> findAll(@Param("shopId") Long shopId);

    @Update("UPDATE  goods_spec SET  status=4 " +
            " WHERE id = #{specId}")
    void delete(Long specId);


}
