package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.parameter.SpuParameter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/23.
 */
@Mapper
public interface SpuParameterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spu_parameter(id,spu_id,parameter_id,parameter_value_id,create_date,update_date,remarks)" +
            "VALUES(#{spuParameterId.id},#{spuId},#{parameterId},#{parameterValueId},now(),now(),#{remarks})")
    int create(SpuParameter spuParameter);

    @Update("UPDATE  goods_spu_parameter SET status=4  WHERE spu_id = #{spuId}")
    int deleteBySpuId(@Param("spuId") Long spuId);

    @Results(
            id = "spuParameterResult",
            value = {
                    @Result(property = "spuParameterId.id", column = "id"),
                    @Result(property = "spuId", column = "spu_id"),
                    @Result(property = "parameterId", column = "parameter_id"),
                    @Result(property = "parameterValueId", column = "parameter_value_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spu_parameter WHERE status = 1 AND spu_id=#{spuId}")
    List<SpuParameter> queryBySpuId(@Param("spuId") Long spuId);
}
