package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.parameter.CategoryParameter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
public interface CategoryParameterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_category_parameter(id,category_id,parameter_id,parameter_value_id,sort,create_date,update_date,remarks) " +
            "VALUES(#{categoryParameterId.id},#{categoryId},#{parameterId},#{parameterValueId},#{sort},#{createDate},#{updateDate},#{remarks})")
    void create(CategoryParameter categoryParameter);

    @Results(
            id = "categoryParameterResult",
            value = {
                    @Result(property = "categoryParameterId.id", column = "id"),
                    @Result(property = "categoryId", column = "category_id"),
                    @Result(property = "parameterId", column = "parameter_id"),
                    @Result(property = "parameterValueId", column = "parameter_value_id"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_category_parameter WHERE status = 1 AND id=#{categoryParameterId}")
    CategoryParameter queryById(@Param("categoryParameterId") Long categoryParameterId);


    @ResultMap("categoryParameterResult")
    @Select("SELECT * FROM goods_category_parameter WHERE status=1 AND category_id=#{categoryId} ORDER BY SORT")
    List<CategoryParameter> findByCategoryId(@Param("categoryId") Long categoryId);

    @Update("UPDATE  goods_category_parameter SET status=4 " +
            " WHERE id = #{categoryParameterId}")
    int delete(@Param("categoryParameterId") Long categoryParameterId);
}
