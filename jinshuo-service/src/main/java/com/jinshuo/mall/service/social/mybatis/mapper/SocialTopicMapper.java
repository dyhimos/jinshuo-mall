package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.SocialTopic;
import com.jinshuo.mall.service.social.application.qry.SocialTopicQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic (id, shop_id, title,attr, is_top, weight,user_id, circle_id, " +
            "reply_count,click_count, up_count, collect_count,topic_time, update_time, audit_status,audit_user, audit_time, contents," +
            "create_date,update_date,remarks,user_type)" +
            "values (#{socialTopicId.id}, #{shopId}, #{title},#{attr}, #{isTop}, #{weight},#{userId}, #{circleId}, " +
            "#{replyCount},#{clickCount}, #{upCount}, #{collectCount},#{topicTime}, #{updateTime}, #{auditStatus},#{auditUser}, #{auditTime}, #{contents}" +
            ",#{createDate}, #{updateDate}, #{remarks}, #{userType})")
    int create(SocialTopic socialTopic);


    @Results(
            id = "socialTopicResult",
            value = {
                    @Result(column="id" , property="socialTopicId.id" ),
                    @Result(column="shop_id" , property="shopId" ),
                    @Result(column="title" , property="title" ),
                    @Result(column="attr" , property="attr" ),
                    @Result(column="is_top" , property="isTop" ),
                    @Result(column="weight" , property="weight" ),
                    @Result(column="user_type" , property="userType" ),
                    @Result(column="user_id" , property="userId" ),
                    @Result(column="circle_id" , property="circleId" ),
                    @Result(column="reply_count" , property="replyCount" ),
                    @Result(column="click_count" , property="clickCount" ),
                    @Result(column="up_count" , property="upCount" ),
                    @Result(column="collect_count" , property="collectCount" ),
                    @Result(column="topic_time" , property="topicTime" ),
                    @Result(column="update_time" , property="updateTime" ),
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
    @Select("SELECT * FROM social_topic WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialTopic queryById(@Param("id") Long id);

    @ResultMap("socialTopicResult")
    @SelectProvider(type = SocialDynamicSql.class, method = "queryTopicByExmple")
    List<SocialTopic> queryTopicByExmple(SocialTopicQry qry);

    @Update("UPDATE  social_topic SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("UPDATE  social_topic SET title = #{title},contents = #{contents},update_time = #{updateTime},update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicId.id}")
    int update(SocialTopic socialTopic);

    @Update("UPDATE  social_topic SET up_count = #{upCount},update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicId.id}")
    int up(SocialTopic socialTopic);

    @Update("UPDATE  social_topic SET collect_count = #{collectCount},update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicId.id}")
    int collect(SocialTopic socialTopic);

    @Update("UPDATE  social_topic SET reply_count = #{replyCount},update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicId.id}")
    int reply(SocialTopic socialTopic);

    @Update("UPDATE  social_topic SET audit_status = #{auditStatus},audit_user = #{auditUser}," +
            "audit_time = #{auditTime}," +
            "update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicId.id}")
    int review(SocialTopic socialTopic);


    @ResultMap("socialTopicResult")
    @SelectProvider(type = SocialDynamicSql.class, method = "queryMyCollecttopic")
    List<SocialTopic> queryMyCollecttopic(SocialTopicQry qry);

    @ResultMap("socialTopicResult")
    @Select("SELECT * FROM social_topic WHERE status = 1 AND user_id=#{userId} AND up_count>0 ORDER BY create_date DESC ")
    List<SocialTopic> quryMyUpedPage(@Param("userId") Long userId);


    @Update("UPDATE  social_topic SET attr = #{attr},update_date = #{updateDate}  " +
            "WHERE id = #{socialTopicId.id}")
    int recommendTopic(SocialTopic socialTopic);
}
