package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.SocialTopicComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicCommentMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic_comment (id, topic_id, user_id,comment_time, audit_status, audit_user," +
            "audit_time, content, create_date,update_date,remarks)" +
            "values (#{socialTopicCommentId.id}, #{topicId}, #{userId},#{commentTime}, #{auditStatus}, #{auditUser}," +
            "#{auditTime}, #{content},#{createDate}, #{updateDate}, #{remarks})")
    int create(SocialTopicComment socialTopicComment);


    @Results(
            id = "socialTopicCommentResult",
            value = {
                    @Result(column="id" , property="socialTopicCommentId.id" ),
                    @Result(column="topic_id" , property="topicId" ),
                    @Result(column="user_id" , property="userId" ),
                    @Result(column="up_count" , property="upCount" ),
                    @Result(column="comment_time" , property="commentTime" ),
                    @Result(column="audit_status" , property="auditStatus" ),
                    @Result(column="audit_user" , property="auditUser" ),
                    @Result(column="audit_time" , property="auditTime" ),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_topic_comment WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialTopicComment queryById(@Param("id") Long id);

    @ResultMap("socialTopicCommentResult")
    @Select("<script>" +
            "SELECT * FROM social_topic_comment WHERE status = 1 " +
            "<if test='topicId != null'> " +
            "AND topic_id = #{topicId}" +
            "</if>" +
            "ORDER BY create_date DESC " +
            "</script>")
    List<SocialTopicComment> queryByTopicId(@Param("topicId") Long topicId);

    @ResultMap("socialTopicCommentResult")
    @Select("<script>" +
            "SELECT * FROM social_topic_comment WHERE status = 1 " +
            "<if test='topicId != null'> " +
            "AND topic_id = #{topicId}" +
            "</if>" +
            "ORDER BY create_date DESC " +
            "</script>")
    List<SocialTopicComment> findByExmple(SocialTopicComment socialTopicComment);

    @Update("UPDATE  social_topic_comment SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("UPDATE  social_topic_comment SET audit_status = #{auditStatus},audit_user = #{auditUser}," +
            "audit_time = #{auditTime}," +
            "update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicCommentId.id}")
    int review(SocialTopicComment socialTopicComment);

    @ResultMap("socialTopicCommentResult")
    @Select("SELECT * FROM social_topic_comment WHERE status = 1 AND audit_status=0 AND topic_id=#{topicId}  ORDER BY create_date DESC ")
    List<SocialTopicComment> findReviewedByTopicId(@Param("topicId") Long topicId);

    @Update("UPDATE  social_topic_comment SET up_count = #{upCount},update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicCommentId.id}")
    int up(SocialTopicComment socialTopicComment);

    @ResultMap("socialTopicCommentResult")
    @Select("SELECT * FROM social_topic_comment WHERE status = 1 AND  user_id=#{userId}  ORDER BY create_date DESC ")
    List<SocialTopicComment> queryByUserId(@Param("userId") Long userId);
}
