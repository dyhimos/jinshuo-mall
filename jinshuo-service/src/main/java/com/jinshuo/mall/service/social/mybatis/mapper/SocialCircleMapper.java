package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.social.SocialCircle;
import org.apache.ibatis.annotations.*;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialCircleMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_circle " +
            "(id, shop_id, name,remark, weight, topic_count,user_count," +
            " create_date,update_date,remarks)" +
            "values (#{socialCircleId.id}, #{shopId}, #{name},#{remark}, #{weight}, #{topicCount},#{userCount},#{createDate}, #{updateDate}, #{remarks})")
    int create(SocialCircle socialCircle);


    @Results(
            id = "socialCircleResult",
            value = {
                    @Result(column="id" , property="socialCircleId,id" ),
                    @Result(column="shop_id" , property="shopId" ),
                    @Result(column="name" , property="name" ),
                    @Result(column="remark" , property="remark" ),
                    @Result(column="weight" , property="weight" ),
                    @Result(column="topic_count" , property="topicCount" ),
                    @Result(column="user_count" , property="userCount" ),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_circle WHERE status = 1 AND mem_id=#{memId} LIMIT 1")
    SocialCircle queryById(@Param("memId") Long memId);


    @Update("UPDATE  social_circle SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);


}
