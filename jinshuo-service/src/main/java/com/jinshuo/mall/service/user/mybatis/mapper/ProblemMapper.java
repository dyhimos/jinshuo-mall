package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.problem.Problem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/11.
 */
@Mapper
public interface ProblemMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" insert into user_shop_problem (id, title,sort, " +
            "content,create_date,update_date,remarks)" +
            "values (#{problemId.id}, #{title},#{sort}," +
            "#{content},#{createDate},#{updateDate},#{remarks})")
    int create(Problem problem);

    @Results(
            id = "problemResult",
            value = {
                    @Result(property = "problemId.id", column = "id"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM user_shop_problem WHERE status = 1 AND id=#{id}")
    Problem queryById(@Param("id") Long id);

    @Update("UPDATE  user_shop_problem SET title=#{title},content=#{content},sort=#{sort}," +
            "update_date=now() " +
            " WHERE id = #{problemId.id}")
    int update(Problem notice);


    @Update("UPDATE  user_shop_problem SET status=4 " +
            " WHERE id = #{id}")
    int delete(@Param("id") Long id);

    @ResultMap("problemResult")
    @Select("<script>" +
            "SELECT * FROM user_shop_problem WHERE status = 1 " +
            "<if test='title != null '> " +
            "AND (title like CONCAT('%',#{title},'%') )" +
            "</if>" +
            "ORDER BY sort DESC,update_date DESC" +
            "</script>")
    List<Problem> queryProblem(Problem problem);

}
