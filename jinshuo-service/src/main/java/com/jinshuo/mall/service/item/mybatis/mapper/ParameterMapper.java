package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.parameter.Parameter;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface ParameterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_parameter " +
            "(id,shop_id,name,type,sort,single_flag," +
            "update_date," +
            "remarks,create_date)" +
            "VALUES(" +
            "#{parameterId.id},#{shopId},#{name},#{type},#{sort},#{singleFlag}," +
            "#{updateDate}," +
            "#{remarks},#{createDate})")
    void create(Parameter parameter);

    @Results(
            id = "parameterResult",
            value = {
                    @Result(property = "parameterId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "single_flag", column = "singleFlag"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "createDate", column = "create_date")
            }
    )
    @Select("SELECT * FROM goods_parameter WHERE status = 1 AND shop_id=#{shopId} ORDER BY sort DESC;")
    List<Parameter> queryByShopId(@Param("shopId") Long shopId);

    @ResultMap("parameterResult")
    @Select("SELECT * FROM goods_parameter WHERE  id=#{id}") //会查出删除的数据
    Parameter queryById(@Param("id") Long id);

    @Update("UPDATE  goods_parameter SET name=#{name},type=#{type} ,single_flag=#{singleFlag} ," +
            "sort=#{sort},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{parameterId.id}")
    int update(Parameter parameter);


    @Update("UPDATE  goods_parameter SET status=4 " +
            " WHERE id = #{parameterId}")
    int delete(@Param("parameterId") Long parameterId);

}
