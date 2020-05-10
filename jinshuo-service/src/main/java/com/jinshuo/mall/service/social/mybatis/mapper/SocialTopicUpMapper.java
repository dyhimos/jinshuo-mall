package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.SocialTopicUp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicUpMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic_up (id,operate_type, topic_id, user_id,up_time," +
            " create_date,update_date,remarks)" +
            "values (#{socialTopicUpId.id},#{operateType}, #{topicId}, #{userId},#{upTime},#{createDate}, #{updateDate}, #{remarks}) ")
    int create(SocialTopicUp socialTopicUp);


    @Results(
            id = "socialTopicUpResult",
            value = {
                    @Result(column = "id", property = "socialTopicUpId.id"),
                    @Result(column = "operate_type", property = "operateType"),
                    @Result(column = "topic_id", property = "topicId"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "up_time", property = "upTime"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_topic_up WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialTopicUp queryById(@Param("id") Long id);

    @ResultMap("socialTopicUpResult")
    @Select("SELECT * FROM social_topic_up WHERE status = 1 AND user_id=#{userId} AND topic_id=#{topicId} LIMIT 1")
    SocialTopicUp findByType(@Param("userId") Long userId, @Param("topicId") Long topicId);

    @Update("UPDATE  social_topic_up SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @ResultMap("socialTopicUpResult")
    @Select("<script>" +
            "SELECT * FROM social_topic_up WHERE status = 1 AND user_id=#{userId} " +
            "<if test='operateType != null'> " +
            "AND operate_type = #{operateType}" +
            "</if>" +
            "ORDER BY create_date DESC " +
            "</script>")
    List<SocialTopicUp> queryByUserId(@Param("userId") Long userId, @Param("operateType") Integer operateType);
}
