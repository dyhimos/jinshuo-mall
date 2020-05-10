package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.topic.SocialTopicAlbum;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialTopicAlbumMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_topic_album (id, topic_id, photo_url,sort, add_time," +
            " create_date,update_date,remarks)" +
            "values (#{socialTopicAlbumId.id}, #{topicId}, #{photoUrl},#{sort}, #{addTime},#{createDate}, #{updateDate}, #{remarks}) ")
    int create(SocialTopicAlbum socialTopicAlbum);


    @Results(
            id = "socialTopicAlbumResult",
            value = {
                    @Result(column="id" , property="socialTopicAlbumId.id" ),
                    @Result(column="topic_id" , property="topicId" ),
                    @Result(column="photo_url" , property="photoUrl" ),
                    @Result(column="sort" , property="sort" ),
                    @Result(column="add_time" , property="addTime" ),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_topic_album WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialTopicAlbum queryById(@Param("id") Long id);

    @Update("UPDATE  social_topic_album SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @ResultMap("socialTopicAlbumResult")
    @Select("SELECT * FROM social_topic_album WHERE status = 1 AND topic_id=#{topicId} ORDER BY sort DESC ")
    List<SocialTopicAlbum> queryByTopicId(@Param("topicId") Long topicId);
}
