package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.SocialTopicCollect;
import org.apache.ibatis.annotations.*;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicCollectMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic_collect (id, topic_id, user_id,collect_time," +
            " create_date,update_date,remarks)" +
            "values (#{socialTopicCollectId.id}, #{topicId}, #{userId},#{collectTime},#{createDate}, #{updateDate}, #{remarks})")
    int create(SocialTopicCollect socialTopicCollect);


    @Results(
            id = "socialTopicCollectResult",
            value = {
                    @Result(column="id" , property="socialTopicCollectId.id" ),
                    @Result(column="topic_id" , property="topicId" ),
                    @Result(column="user_id" , property="userId" ),
                    @Result(column="collect_time" , property="collectTime" ),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_topic_collect WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialTopicCollect queryById(@Param("id") Long id);

    @Update("UPDATE  social_topic_collect SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @ResultMap("socialTopicCollectResult")
    @Select("SELECT * FROM social_topic_collect WHERE status = 1 AND user_id=#{userId} AND topic_id=#{topicId} LIMIT 1")
    SocialTopicCollect findByType(@Param("userId") Long userId, @Param("topicId") Long topicId);
}
