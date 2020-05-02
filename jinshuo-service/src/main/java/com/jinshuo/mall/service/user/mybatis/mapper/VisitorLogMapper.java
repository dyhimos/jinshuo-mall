package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.statistics.VisitorLog;
import com.jinshuo.mall.service.user.application.qry.StatisticsQry;
import com.jinshuo.mall.service.user.application.qry.VisitorCountQry;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/12/11.
 */
@Mapper
public interface VisitorLogMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" insert into user_visitor_log (id,user_id,ip_addr,visit_count,visit_first_time,visit_last_time," +
            "create_date,update_date,remarks)" +
            "values (#{visitorLogId.id},#{userId},#{ipAddr},#{visitCount},#{visitFirstTime},#{visitLastTime}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(VisitorLog visitorLog);

    @Results(
            id = "visitorLogResult",
            value = {
                    @Result(property = "visitorLogId.id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "ipAddr", column = "ip_addr"),
                    @Result(property = "visitCount", column = "visit_count"),
                    @Result(property = "visitFirstTime", column = "visit_first_time"),
                    @Result(property = "visitLastTime", column = "visit_last_time"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM user_visitor_log WHERE status = 1 AND id=#{id}")
    VisitorLog queryById(@Param("id") Long id);


    @Update("UPDATE  user_visitor_log SET visit_count=#{visitCount},visit_last_time=#{visitLastTime},update_date=now() " +
            " WHERE id = #{visitorLogId.id}")
    int updateCount(VisitorLog visitorLog);

    @ResultMap("visitorLogResult")
    @Select("SELECT * FROM user_visitor_log WHERE status = 1 AND ip_addr=#{ipAddr} AND TO_DAYS(NOW()) = TO_DAYS(visit_last_time) LIMIT 1")
    VisitorLog queryByIpAddr(@Param("ipAddr") String ipAddr);

    @ResultMap("visitorLogResult")
    @Select("SELECT * FROM user_visitor_log WHERE status = 1 AND user_id=#{userId} AND TO_DAYS(NOW()) = TO_DAYS(visit_last_time) LIMIT 1")
    VisitorLog queryByIpUserId(@Param("userId") Long userId);


    @ResultMap("visitorLogResult")
    @Select("<script>" +
            "SELECT * FROM user_visitor_log WHERE status = 1 " +
            "<if test='title != null '> " +
            "AND title = #{title}" +
            "</if>" +
            "ORDER BY sort DESC,update_date DESC" +
            "</script>")
    List<VisitorLog> queryNotice(VisitorLog visitorLog);

    @ResultMap("visitorLogResult")
    @SelectProvider(type = DynamicSql.class, method = "countMember")
    List<VisitorLog> countMember(VisitorCountQry qry);

    @ResultMap("visitorLogResult")
    @Select("SELECT * FROM user_visitor_log WHERE status = 1 AND TO_DAYS( #{date}) = TO_DAYS( visit_last_time )")
    List<VisitorLog> queryByCreateDate(@Param("date") Date date);

    @Results(
            id = "visitorLogResult1",
            value = {
                    @Result(property = "visitorLogId.id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "ipAddr", column = "ip_addr"),
                    @Result(property = "visitCount", column = "visit_count"),
                    @Result(property = "visitFirstTime", column = "visit_first_time"),
                    @Result(property = "visitLastTime", column = "visit_last_time"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT TO_DAYS(create_date),create_date,visit_last_time,count(1) as visit_count FROM user_visitor_log WHERE status = 1 GROUP BY TO_DAYS(visit_last_time) ORDER BY visit_last_time DESC ")
    List<VisitorLog> conversionRateList(StatisticsQry qry);

    @ResultType(Integer.class)
    @Select("SELECT count(1)  FROM user_visitor_log WHERE status = 1 AND user_id is not null AND TO_DAYS(visit_last_time) = TO_DAYS( #{date}) ")
    Integer conversionRateListRegist(@Param("date") Date date);
}
