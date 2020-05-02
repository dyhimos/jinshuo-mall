package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.collect.Collect;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface CollectMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO member_collect(" +
            "id,mem_id,type,target_id," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{collectId.id},#{memId.id},#{type},#{targetId}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(Collect tag);

    @Results(
            id = "goodstagResult",
            value = {
                    @Result(property = "collectId.id", column = "id"),
                    @Result(property = "memId.id", column = "mem_id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "targetId", column = "target_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM member_collect WHERE status = 1 AND id=#{collectId} LIMIT 1 ")
    Collect queryById(@Param("collectId") Long collectId);

    @Update("UPDATE  member_collect SET mem_id=#{memId.id},type=#{type} ," +
            "target_id=#{targetId},create_date=#{createDate},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{collectId.id}")
    int update(Collect tag);

    @ResultMap("goodstagResult")
    @Select("SELECT * FROM member_collect WHERE status=1 AND mem_id=#{memId} ORDER BY create_date DESC ")
    List<Collect> findAll(@Param("memId") Long memId);

    @Update("UPDATE  member_collect SET status=4 " +
            " WHERE target_id = #{targetId} AND mem_id=#{memId} ")
    int delete(@Param("memId") Long memId, @Param("targetId") Long targetId);


    @ResultMap("goodstagResult")
    @Select("SELECT * FROM member_collect WHERE status=1 AND mem_id=#{memId} AND target_id=#{targetId} ORDER BY create_date DESC ")
    List<Collect> queryByTargetId(@Param("memId") Long memId, @Param("targetId") Long targetId);

}
