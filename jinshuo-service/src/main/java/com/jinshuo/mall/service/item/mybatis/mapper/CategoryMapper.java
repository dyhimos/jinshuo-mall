package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.category.model.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/7/3.
 */
@Mapper
public interface CategoryMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into goods_category(id,pid,leaf,level,name,cate_type,order_seq,picture_url,back_categories,need_audit,version,create_date,update_date,remarks,shop_id,is_show)" +
            " VALUES(#{categoryId.id},#{pid.id} ,#{leaf},#{categoryLevel.level},#{name},#{cateType},#{orderSeq},#{pictureUrl},#{backCategories},#{needAudit},#{version},#{createDate},#{updateDate},#{remarks},#{shopId},#{isShow})")
    void create(Category category);

    @Update("update goods_category set " +
            "pid = #{pid.id}," +
            "leaf = #{leaf}," +
            "level = #{categoryLevel.level}," +
            "name = #{name}," +
            "shop_id = #{shopId}," +
            "is_show = #{isShow}," +
            "cate_type = #{cateType}," +
            "order_seq = #{orderSeq}," +
            "picture_url = #{pictureUrl}," +
            "back_categories = #{backCategories}," +
            "need_audit = #{needAudit}," +
            "version = #{version}," +
            "create_date = #{createDate}," +
            "update_date = now()," +
            "remarks = #{remarks}" +
            " WHERE id = #{categoryId.id} ")
    int update(Category category);

    @Update("update goods_category set status='4' where id = #{categoryId}")
    void delete(@Param("categoryId") Long categoryId);

    @Results(id = "categoryResult", value = {
            @Result(id = true, property = "categoryId.id", column = "id"),
            @Result(property = "pid.id", column = "pid"),
            @Result(property = "leaf", column = "leaf"),
            @Result(property = "categoryLevel.level", column = "level"),
            @Result(property = "name", column = "name"),
            @Result(property = "shopId", column = "shop_id"),
            @Result(property = "isShow", column = "is_show"),
            @Result(property = "cateType", column = "cate_type"),
            @Result(property = "orderSeq", column = "order_seq"),
            @Result(property = "pictureUrl", column = "picture_url"),
            @Result(property = "backDategories", column = "back_dategories"),
            @Result(property = "needAudit", column = "need_audit"),
            @Result(property = "status.code", column = "status"),
            @Result(property = "version", column = "version"),
            @Result(property = "remarks", column = "remarks"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })

    @Select("select * from goods_category where  id=#{categoryId} and status='1' limit 1")
    Category findById(@Param("categoryId") Long categoryId);

    @ResultMap("categoryResult")
    @Select("SELECT * FROM goods_category WHERE status='1' ORDER BY update_date DESC")
    List<Category> findAll();

    @ResultMap("categoryResult")
    @Select("<script>" +
            "SELECT * FROM goods_category WHERE status=1 " +
            "<if test='categoryLevel != null'> " +
            "AND level = #{categoryLevel.level}" +
            "</if>" +
            "<if test='pid != null'> " +
            "AND pid = #{pid.id}" +
            "</if>" +
            "<if test='isShow != null'> " +
            "AND is_show = #{isShow}" +
            "</if>" +
            "<if test='shopId != null'> " +
            "AND shop_id = #{shopId}" +
            "</if>" +
            " ORDER BY order_seq  Desc " +
            "</script>")
    List<Category> findByExample(Category category);


}
