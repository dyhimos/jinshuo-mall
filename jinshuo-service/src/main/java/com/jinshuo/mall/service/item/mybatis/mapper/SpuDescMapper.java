package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.desc.SpuDesc;
import org.apache.ibatis.annotations.*;

/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SpuDescMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spu_desc" +
            "(id,spu_id,pc_desc,mobile_desc,booking_notes,create_date,update_date,remarks)" +
            "VALUES(#{spuDescId.id},#{spuId.id},#{pcDesc},#{mobileDesc},#{bookingNotes}," +
            "#{createDate},#{updateDate},#{remarks})")
    void create(SpuDesc spuDesc);

    @Results(
            id = "spudescResult",
            value = {
                    @Result(property = "spuDescId.id", column = "id"),
                    @Result(property = "spuId.id", column = "spu_id"),
                    @Result(property = "pcDesc", column = "pc_desc"),
                    @Result(property = "mobileDesc", column = "mobile_desc"),
                    @Result(property = "booking_notes", column = "bookingNotes"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spu_desc WHERE id=#{id}")
    SpuDesc findById(@Param("id") Long id);

    @ResultMap("spudescResult")
    @Select("SELECT * FROM goods_spu_desc WHERE spu_id=#{spuId} ORDER BY create_date DESC LIMIT 1")
    SpuDesc findBySpuId(@Param("spuId") Long spuId);

    @Update("UPDATE  goods_spu_desc SET pc_desc=#{pcDesc},mobile_desc=#{mobileDesc}," +
            "booking_notes=#{bookingNotes},update_date=now()" +
            " WHERE id = #{spuDescId.id}")
    int update(SpuDesc spuDesc);

}
