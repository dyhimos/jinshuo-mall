package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.domain.social.social.SocialCircleUser;
import org.apache.ibatis.annotations.*;

/**
 * Created by 19458 on 2019/12/25.
 */
public interface SocialCircleUserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into social_circle_user " +
            "(id, circle_id, user_id,time," +
            " create_date,update_date,remarks)" +
            "values (#{id}, #{circleId}, #{userId},#{time},#{createDate}, #{updateDate}, #{remarks})")
    int create(SocialCircleUser socialCircleUser);


    @Results(
            id = "socialCircleUserResult",
            value = {
                    @Result(column="id" , property="id" ),
                    @Result(column="circle_id" , property="circleId" ),
                    @Result(column="user_id" , property="userId" ),
                    @Result(column="time" , property="time" ),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM social_circle_user WHERE status = 1 AND id=#{id} LIMIT 1")
    SocialCircleUser queryById(@Param("id") Long id);

    @Update("UPDATE  social_circle_user SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}
