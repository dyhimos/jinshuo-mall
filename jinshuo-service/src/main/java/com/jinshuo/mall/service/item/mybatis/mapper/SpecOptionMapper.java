package com.jinshuo.mall.service.item.mybatis.mapper;


import com.jinshuo.mall.domain.item.spec.SpecOption;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/7/17.
 */
@Mapper
public interface SpecOptionMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spec_option(id,name,spec_id,sort,create_date,update_date,remarks) " +
            "VALUES(#{specOptionId.id},#{name},#{specId.id},#{sort},#{createDate},#{updateDate},#{remarks})")
    void create(SpecOption specOption);

    @Options(keyProperty = "id")
    @Insert("UPDATE  goods_spec_option  set name = #{name} ,spec_id = #{specId.id} ,sort = #{sort}," +
            "create_date = #{createDate},update_date = #{updateDate},remarks= #{remarks} " +
            "WHERE id = #{specOptionId.id}")
    void update(SpecOption specOption);

    @Update("update goods_spec_option set status='4' where spec_id = #{specId}")
    int deleteBySpecId(@Param("specId") Long specId);

    @Results(
            id = "specOption",
            value = {
                    @Result(property = "specOptionId.id", column = "id"),
                    @Result(property = "specId.id", column = "spec_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spec_option WHERE status=1 AND spec_id=#{specId} ORDER BY sort DESC ")
    List<SpecOption> findBySpecId(@Param("specId") Long specId);

    @ResultMap("specOption")
    @Select("SELECT * FROM goods_spec_option WHERE status='1' AND id=#{specOptionId}")
    SpecOption queryById(@Param("specOptionId") Long specOptionId);

    @Update("update goods_spec_option set status='4' where id = #{specOptionId}")
    int deleteById(@Param("specOptionId") Long specOptionId);
}
