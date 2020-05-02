package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.topic.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface TopicMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_topic(id,pid,sort,type,code,name,start_time,end_time,shop_id,create_date,update_date,remarks," +
            "main_picture,sign_picture,poster_picture,color,show_type,heading_show_flag,heading_color" +
            ") " +
            "VALUES(#{topicId.id},#{pid},#{sort},#{type},#{code},#{name},#{startTime},#{endTime},#{shopId},#{createDate},#{updateDate},#{remarks}," +
            "#{mainPicture},#{signPicture},#{posterPicture},#{color},#{showType},#{headingShowFlag},#{headingColor}" +
            ")")
    void create(Topic topic);

    @Results(
            id = "topicResult",
            value = {
                    @Result(property = "topicId.id", column = "id"),
                    @Result(property = "code", column = "code"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "pid", column = "pid"),
                    @Result(property = "startTime", column = "start_time"),
                    @Result(property = "endTime", column = "end_time"),
                    @Result(property = "topicStatus", column = "topic_status"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "showType", column = "show_type"),
                    @Result(property = "headingShowFlag", column = "heading_show_flag"),
                    @Result(property = "headingColor", column = "heading_color"),
                    @Result(property = "topicDesc", column = "topic_desc"),
                    @Result(property = "mainPicture", column = "main_picture"),
                    @Result(property = "signPicture", column = "sign_picture"),
                    @Result(property = "posterPicture", column = "poster_picture"),
                    @Result(property = "color", column = "color"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_topic WHERE status = 1 AND id=#{topicId}")
    Topic queryById(@Param("topicId") Long topicId);

    @Update("UPDATE  goods_topic SET sort=#{sort},type=#{type},pid=#{pid},code=#{code},name=#{name},start_time=#{startTime},end_time=#{endTime},topic_status=#{topicStatus},shop_id=#{shopId}," +
            "topic_desc=#{topicDesc},main_picture=#{mainPicture},sign_picture=#{signPicture},poster_picture=#{posterPicture}," +
            "color=#{color},show_type=#{showType},heading_show_flag=#{headingShowFlag},heading_color=#{headingColor}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{topicId.id}")
    void update(Topic topic);

    @ResultMap("topicResult")
    @Select("<script>" +
            "SELECT * FROM goods_topic WHERE status=1 AND shop_id=#{shopId} " +
            "<if test='type != null'> " +
            "AND type = #{type}" +
            "</if>" +
            "ORDER BY create_date DESC " +
            "</script>")
    List<Topic> findAll(Topic topic);

    @Update("UPDATE  goods_topic SET status=4 " +
            " WHERE id = #{topicId}")
    int delete(@Param("topicId") Long topicId);

    @ResultMap("topicResult")
    @Select("SELECT * FROM goods_topic WHERE status = 1 AND code=#{code} AND shop_id=#{shopId} LIMIT 1")
    Topic queryByCode(@Param("code") String code, @Param("shopId") Long shopId);

    @Update("UPDATE  goods_topic SET topic_status=topicStatus " +
            " WHERE id = #{topicId}")
    int updateStatus(@Param("topicId") Long topicId, @Param("adStatus") Integer topicStatus);

    @ResultMap("topicResult")
    @Select("SELECT * FROM goods_topic WHERE status = 1 AND pid=#{id} ORDER BY sort DESC ")
    List<Topic> queryByPid(@Param("id") Long id);

}
