package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.statistics.Statistics;
import com.jinshuo.mall.service.user.application.qry.StatisticsQry;
import com.jinshuo.mall.service.user.application.qry.VisitorCountQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/11.
 */
@Mapper
public interface StatisticsMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" insert into user_statistics (id, statistics_type, statistics_code,count," +
            "create_date,update_date,remarks)" +
            "values (#{statisticsId.id}, #{statisticsType},  #{statisticsCode},#{count}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(Statistics statistics);

    @Results(
            id = "statisticsResult",
            value = {
                    @Result(property = "statisticsId.id", column = "id"),
                    @Result(property = "statisticsType", column = "statistics_type"),
                    @Result(property = "statisticsCode", column = "statistics_code"),
                    @Result(property = "count", column = "count"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM user_statistics WHERE status = 1 AND id=#{id}")
    Statistics queryById(@Param("id") Long id);

    @ResultMap("statisticsResult")
    @Select("SELECT * FROM user_statistics WHERE status = 1  AND statistics_code=#{statisticsCode} AND to_days(create_date) = to_days(now()) LIMIT 1")
    Statistics queryByCode(@Param("statisticsCode") String statisticsCode);


    @Update("UPDATE  user_statistics SET count=#{count},update_date=now() " +
            " WHERE id = #{statisticsId.id}")
    int updateCount(Statistics statistics);

    @Update("UPDATE  user_statistics SET status=4 " +
            " WHERE id = #{id}")
    int delete(@Param("id") Long id);


    @ResultMap("statisticsResult")
    @SelectProvider(type = DynamicSql.class, method = "countStatistics")
    List<Statistics> countStatistics(VisitorCountQry qry);

    @Results(
            id = "statisticsResult1",
            value = {
                    @Result(property = "count", column = "count"),
                    @Result(property = "createDate", column = "create_date"),
            }
    )
    @Select("SELECT TO_DAYS(create_date),create_date,SUM(`count`) as count FROM `user_statistics` GROUP BY TO_DAYS(create_date) ORDER BY CREATE_DATE DESC ")
    List<Statistics> countStatisticsList(StatisticsQry qry);

}
