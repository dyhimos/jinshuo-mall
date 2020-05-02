package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.shopMessageConfig.ShopMessageConfig;
import org.apache.ibatis.annotations.*;


/**
 * 短信配置信息mapper
 *
 * @Classname ShopMessageConfigMapper
 * @Description TODO
 * @Date 2019/9/16 20:09
 * @Created by dyh
 */
@Mapper
public interface ShopMessageConfigMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO shop_message_config(" +
            "id,shop_id,username,password,sign_name,total,used,remaining," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{shopMessageConfigId.id},#{shopId},#{username},#{password},#{signName},#{total},#{used},#{remaining}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(ShopMessageConfig shopMessageConfig);

    @Results(
            id = "shopResult",
            value = {
                    @Result(property = "shopMessageConfigId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "signName", column = "sign_name"),
                    @Result(property = "total", column = "total"),
                    @Result(property = "used", column = "used"),
                    @Result(property = "remaining", column = "remaining"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM shop_message_config WHERE status = 1 AND id=#{id} LIMIT 1 ")
    ShopMessageConfig queryById(@Param("id") Long id);


    /**
     * 根据店铺查询对应的短信信息
     *
     * @param shopId
     * @return
     */
    @ResultMap("shopResult")
    @Select("SELECT * FROM shop_message_config WHERE status = 1 AND shop_id=#{shopId}")
    ShopMessageConfig queryByShopId(@Param("shopId") Long shopId);

    @Update("UPDATE  shop_message_config SET status=4 " +
            " WHERE id = #{id} ")
    int delete(@Param("id") Long id);


    @Update("<script>" +
            "update  shop_message_config set " +
            "<if test='username != null and username !=\"\"'> " +
            " username = #{username}," +
            "</if>" +
            "<if test='password != null and password !=\"\"'> " +
            " password = #{password}," +
            "</if>" +
            "<if test='signName != null and signName !=\"\"'> " +
            " sign_name = #{signName}," +
            "</if>" +
            "<if test='total !=\"\"'> " +
            " total = #{total}," +
            "</if>" +
            "<if test='used !=\"\"'> " +
            " used = #{used}," +
            "</if>" +
            "<if test='remaining !=\"\"'> " +
            " remaining = #{remaining}," +
            "</if>" +
            "where id= #{shopMessageConfigId.id} " +
            "</script>")
    int update(ShopMessageConfig shopMessageConfig);
}
