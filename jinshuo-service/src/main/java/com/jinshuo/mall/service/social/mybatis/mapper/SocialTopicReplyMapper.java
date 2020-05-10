package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.SocialTopicReply;
import org.apache.ibatis.annotations.*;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicReplyMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic_reply (id, comment_id, reply_type,reply_id, user_id, to_user_id,reply_time," +
            " audit_status, audit_user,audit_time, content, create_date,update_date,remarks)" +
            "values (#{socialTopicReplyId.id}, #{commentId}, #{replyType},#{replyId}, #{userId}, #{toUserId},#{replyTime}, " +
            "#{auditStatus}, #{auditUser},#{auditTime}, #{content},#{createDate}, #{updateDate}, #{remarks})")
    int create(SocialTopicReply socialTopicReply);


    @Results(
            id = "socialTopicReplyResult",
            value = {
                    @Result(column="id" , property="socialTopicReplyId.id" ),
                    @Result(column="comment_id" , property="commentId" ),
                    @Result(column="reply_type" , property="replyType" ),
                    @Result(column="reply_id" , property="replyId" ),
                    @Result(column="user_id" , property="userId" ),
                    @Result(column="to_user_id" , property="toUserId" ),
                    @Result(column="reply_time" , property="replyTime" ),
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
    @Select("SELECT * FROM social_topic_reply WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialTopicReply queryById(@Param("id") Long id);

    @Update("UPDATE  social_topic_reply SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("UPDATE  social_topic_reply SET audit_status = #{auditStatus},audit_user = #{auditUser}," +
            "audit_time = #{auditTime}," +
            "update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicReplyId.id}")
    int review(SocialTopicReply socialTopicReply);
}
