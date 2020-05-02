package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.ad.AdPosition;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface AdPositionMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO cms_ad_position(id,name,`desc`,is_cycle,shop_id,create_date,update_date,remarks,code," +
            "ad_status,start_time,end_time,show_type,gap_time,area_ids,member_type" +
            ") " +
            "VALUES(#{adPositionId.id},#{name},#{desc},#{isCycle},#{shopId},#{createDate},#{updateDate},#{remarks},#{code}," +
            "#{adStatus},#{startTime},#{endTime},#{showType},#{gapTime},#{areaIds},#{memberType}" +
            ")")
    void create(AdPosition adPosition);

    @Results(
            id = "adPositionResult",
            value = {
                    @Result(property = "adPositionId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "code", column = "code"),
                    @Result(property = "desc", column = "desc"),
                    @Result(property = "isCycle", column = "is_cycle"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "adStatus", column = "ad_status"),
                    @Result(property = "startTime", column = "start_time"),
                    @Result(property = "endTime", column = "end_time"),
                    @Result(property = "showType", column = "show_type"),
                    @Result(property = "gapTime", column = "gap_time"),
                    @Result(property = "areaIds", column = "area_ids"),
                    @Result(property = "memberType", column = "member_type"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM cms_ad_position WHERE status = 1 AND id=#{adPositionId}")
    AdPosition queryById(@Param("adPositionId") Long adPositionId);

    @Update("UPDATE  cms_ad_position SET code=#{code},name=#{name},`desc`=#{desc},is_cycle=#{isCycle},shop_id=#{shopId}," +
            "ad_status=#{adStatus},start_time=#{startTime},end_time=#{endTime},show_type=#{showType},gap_time=#{gapTime},area_ids=#{areaIds}," +
            "member_type=#{memberType}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{adPositionId.id}")
    void update(AdPosition adPosition);

    @ResultMap("adPositionResult")
    @Select("SELECT * FROM cms_ad_position WHERE status=1 AND shop_id=#{shopId} ORDER BY create_date DESC ")
    List<AdPosition> findAll(AdPosition adPosition);

    @Update("UPDATE  cms_ad_position SET status=4 " +
            " WHERE id = #{adPositionId}")
    int delete(@Param("adPositionId") Long adPositionId);


    @ResultMap("adPositionResult")
    @Select("SELECT * FROM cms_ad_position WHERE status = 1 AND code=#{code} AND shop_id=#{shopId} LIMIT 1")
    AdPosition queryByCode(@Param("code") String code, @Param("shopId") Long shopId);

    @Update("UPDATE  cms_ad_position SET ad_status=adStatus " +
            " WHERE id = #{adPositionId}")
    int updateStatus(@Param("adPositionId") Long adPositionId, @Param("adStatus") Integer adStatus);

}
