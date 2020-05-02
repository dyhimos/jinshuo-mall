package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.service.ServiceSupport;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface ServiceSupportMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_service_support(id,shop_id,name,ss_status,`desc`,sort,create_date,update_date,remarks" +
            ") " +
            "VALUES(#{serviceSupportId.id},#{shopId},#{name},#{ssStatus},#{desc},#{sort},#{createDate},#{updateDate},#{remarks}" +
            ")")
    void create(ServiceSupport serviceSupport);

    @Results(
            id = "serviceResult",
            value = {
                    @Result(property = "serviceSupportId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "ssStatus", column = "ss_status"),
                    @Result(property = "desc", column = "desc"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_service_support WHERE status = 1 AND id=#{adPositionId}")
    ServiceSupport queryById(@Param("topicProductId") Long topicProductId);


    @ResultMap("serviceResult")
    @Select("<script>" +
            "SELECT * FROM goods_service_support WHERE status=1 " +
            "<if test='shopId != null'> " +
            "AND shop_id = #{shopId}" +
            "</if>" +
            "ORDER BY sort DESC " +
            "</script>")
    List<ServiceSupport> findAll(ServiceSupport serviceSupport);

    @Update("UPDATE  goods_service_support SET name=#{name},ss_status=#{ssStatus},desc=#{desc},sort=#{sort} " +
            " WHERE id = #{serviceSupportId.Id}")
    int update(ServiceSupport serviceSupport);

    @Update("UPDATE  goods_service_support SET ss_status=#{ssStatus} " +
            " WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("sort") Integer ssStatus);

    @Update("UPDATE  goods_service_support SET status=4 " +
            " WHERE id = #{topicProductId}")
    int delete(@Param("serviceSupportId") Long serviceSupportId);


    @Update("UPDATE  goods_service_support SET status=4,sort=#{sort}" +
            " WHERE id = #{id}")
    int updateProductSort(@Param("id") Long id, @Param("sort") Integer sort);

}
