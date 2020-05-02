package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.parameter.ParameterValue;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface ParameterValueMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_parameter_value " +
            "(id,parameter_id,name,type,sort,param," +
            "update_date," +
            "remarks,create_date)" +
            "VALUES(" +
            "#{parameterValueId.id},#{parameterId},#{name},#{type},#{sort},#{param}," +
            "#{updateDate}," +
            "#{remarks},#{createDate})")
    void create(ParameterValue parameterValue);

    @Results(
            id = "parameterResult",
            value = {
                    @Result(property = "parameterValueId.id", column = "id"),
                    @Result(property = "parameterId", column = "parameter_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "param", column = "param"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "createDate", column = "create_date")
            }
    )
    @Select("SELECT * FROM goods_parameter_value WHERE status = 1 AND parameter_id=#{parameterId} ORDER BY sort DESC")
    List<ParameterValue> queryByParameterId(@Param("parameterId") Long parameterId);

    @ResultMap("parameterResult")
    @Select("SELECT * FROM goods_parameter_value WHERE status = 1 AND id=#{id}")
    ParameterValue queryById(@Param("id") Long id);

    @Update("UPDATE  goods_parameter_value SET param_name=#{paramName},name=#{name},type=#{type} ," +
            "sort=#{sort},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{parameterValueId.id}")
    int update(ParameterValue parameter);

    @Update("UPDATE  goods_parameter_value SET status=4 " +
            " WHERE id = #{parameterValueId}")
    int delete(@Param("parameterValueId") Long parameterValueId);

    @Update("UPDATE  goods_parameter_value SET status=4 " +
            " WHERE parameter_id = #{parameterId}")
    int deleteByParameterId(@Param("parameterId") Long parameterId);

}
