package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.notice.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/11.
 */
@Mapper
public interface NoticeMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" insert into user_notice (id, title, url," +
            "attachment, is_show, notice_status,sort,start_time,end_time," +
            "content,create_date,update_date,remarks)" +
            "values (#{noticeId.id}, #{title},  #{url}," +
            "#{attachment}, #{isShow}, #{noticeStatus},#{sort},#{startTime},#{endTime}," +
            "#{content},#{createDate},#{updateDate},#{remarks})")
    int create(Notice notice);

    @Results(
            id = "noticeResult",
            value = {
                    @Result(property = "noticeId.id", column = "id"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "attachment", column = "attachment"),
                    @Result(property = "isShow", column = "is_show"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "url", column = "url"),
                    @Result(property = "noticeStatus", column = "notice_status"),
                    @Result(property = "startTime", column = "start_time"),
                    @Result(property = "endTime", column = "end_time"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM user_notice WHERE status = 1 AND id=#{id}")
    Notice queryById(@Param("id") Long id);

    @Update("UPDATE  user_notice SET title=#{title},url=#{url},content=#{content},attachment=#{attachment}," +
            "is_show=#{isShow},sort=#{sort},start_time=#{startTime},end_time=#{endTime}," +
            "notice_status=#{noticeStatus},update_date=now() " +
            " WHERE id = #{noticeId.id}")
    int update(Notice notice);


    @Update("UPDATE  user_notice SET status=4 " +
            " WHERE id = #{id}")
    int delete(@Param("id") Long id);

    @ResultMap("noticeResult")
    @Select("<script>" +
            "SELECT * FROM user_notice WHERE status = 1 " +
            "<if test='title != null '> " +
            "AND title = #{title}" +
            "</if>" +
            "ORDER BY sort DESC,update_date DESC" +
            "</script>")
    List<Notice> queryNotice(Notice notice);

    @ResultMap("noticeResult")
    @SelectProvider(type = UserDynamicSql.class, method = "queryMyNotice")
    List<Notice> queryMyNotice(Notice notice);

}
