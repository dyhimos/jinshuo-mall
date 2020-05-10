package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.TopicShield;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicShieldMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic_shield " +
            "(id, user_id,shield_type, target_id," +
            " create_date,update_date,remarks)" +
            "values (#{id}, #{userId},#{shieldType}, #{targetId},#{createDate}, #{updateDate}, #{remarks})")
    int create(TopicShield topicShield);

    @Results(
            id = "topicShieldResult",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "shield_type", property = "shieldType"),
                    @Result(column = "target_id", property = "targetId"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_topic_shield WHERE status = 1 AND user_id=#{userId} LIMIT 1")
    List<TopicShield> queryByUserId(@Param("userId") Long userId);

    @Update("UPDATE  social_topic_shield SET status=4  WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);

    @Update("UPDATE  social_topic_shield SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

}
