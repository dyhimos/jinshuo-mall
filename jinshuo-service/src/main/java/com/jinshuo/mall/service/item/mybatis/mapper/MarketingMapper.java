package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.marketing.Marketing;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface MarketingMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO marketing(id,name,`desc`,marketing_type,shop_id,create_date,update_date,remarks," +
            "start_time,end_time,goods_mode,quantity,sort,marketing_status" +
            ") " +
            "VALUES(#{marketingId.id},#{name},#{desc},#{marketingType},#{shopId},#{createDate},#{updateDate},#{remarks}," +
            "#{startTime},#{endTime},#{goodsMode},#{quantity},#{sort},#{marketingStatus}" +
            ")")
    void create(Marketing marketing);

    @Results(
            id = "marketingResult",
            value = {
                    @Result(property = "marketingId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "desc", column = "desc"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "marketingType", column = "marketing_type"),
                    @Result(property = "startTime", column = "start_time"),
                    @Result(property = "endTime", column = "end_time"),
                    @Result(property = "goodsMode", column = "goods_mode"),
                    @Result(property = "quantity", column = "quantity"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "marketingStatus", column = "marketing_status"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM marketing WHERE status = 1 AND id=#{marketingId}")
    Marketing queryById(@Param("marketingId") Long marketingId);

    @Update("UPDATE  marketing SET name=#{name},`desc`=#{desc},marketing_type=#{marketingType},shop_id=#{shopId}," +
            "start_time=#{startTime},end_time=#{endTime},goods_mode=#{goodsMode},quantity=#{quantity},sort=#{sort}," +
            "update_date =now(),remarks =#{remarks},marketing_status =#{marketingStatus}" +
            " WHERE id = #{marketingId.id}")
    void update(Marketing marketing);

    @ResultMap("marketingResult")
    @Select("SELECT * FROM marketing WHERE status=1 AND shop_id=#{shopId} ORDER BY sort DESC,create_date DESC ")
    List<Marketing> findAll(Marketing marketing);

    @Update("UPDATE  marketing SET status=4 " +
            " WHERE id = #{marketingId}")
    int delete(@Param("marketingId") Long marketingId);

    @Update("UPDATE  marketing SET marketing_status =#{marketingStatus} " +
            " WHERE id = #{marketingId}")
    int updateStatus(@Param("marketingId") Long marketingId, @Param("marketingStatus") Integer marketingStatus);

}
