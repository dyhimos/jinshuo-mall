package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.topic.TopicProduct;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface TopicProductMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_topic_product(id,topic_id,spu_id,sort,create_date,update_date,remarks" +
            ") " +
            "VALUES(#{topicProductId.id},#{topicId},#{spuId},#{sort},#{createDate},#{updateDate},#{remarks}" +
            ")")
    void create(TopicProduct topicProduct);


    @Insert("<script>" +
            "INSERT INTO goods_topic_product(id,topic_id,spu_id,sort,create_date,update_date,remarks" +
            ") " +
            "VALUES" +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.topicProductId.id},#{item.topicId},#{item.spuId},#{item.sort},#{item.createDate},#{item.updateDate},#{item.remarks})" +
            "</foreach>" +
            "</script>")
    void createList(List<TopicProduct> list);

    @Results(
            id = "topicProductPositionResult",
            value = {
                    @Result(property = "topicProductId.id", column = "id"),
                    @Result(property = "topicId", column = "topic_id"),
                    @Result(property = "spuId", column = "spu_id"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_topic_product WHERE status = 1 AND id=#{adPositionId}")
    TopicProduct queryById(@Param("topicProductId") Long topicProductId);


    @ResultMap("topicProductPositionResult")
    @Select("SELECT * FROM goods_topic_product WHERE status=1 AND topic_id=#{topicId} ORDER BY create_date DESC ")
    List<TopicProduct> findAll(@Param("topicId") Long topicId);

    @Update("UPDATE  goods_topic_product SET status=4 " +
            " WHERE id = #{topicProductId}")
    int delete(@Param("topicProductId") Long topicProductId);

    @Update("UPDATE  goods_topic_product SET status=4 " +
            " WHERE topic_id = #{topicId}")
    int deleteByTopicId(@Param("topicId") Long topicId);

    @Update("UPDATE  goods_topic_product SET sort=#{sort}" +
            " WHERE id = #{id}")
    int updateProductSort(@Param("id") Long id, @Param("sort") Integer sort);

}
