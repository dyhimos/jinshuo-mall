package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.unit.model.GoodsUnit;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by dyh
 * Time 2019/7/19 下午4:54
 */
@Mapper
public interface GoodUnitMapper {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO goods_unit(id,name,create_date,update_date,remarks) VALUES (#{unitId.id},#{name},#{createDate},#{updateDate},#{remarks});")
    void create(GoodsUnit unit);

    @Update("UPDATE goods_unit SET name=#{name} WHERE  id=#{unitId.id}")
    void update(GoodsUnit unit);

    @Update("UPDATE goods_unit SET status='4' WHERE  id=#{id}")
    int delete(@Param("id") Long id);

    @Results(
            id = "unitDetail",
            value = {
                    @Result(property = "unitId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_unit WHERE id=#{id}")
    GoodsUnit findById(@Param("id") Long id);


    @ResultMap("unitDetail")
    @Select("SELECT * FROM goods_unit WHERE status=1 ORDER BY sort DESC")
    List<GoodsUnit> findAll();
}
