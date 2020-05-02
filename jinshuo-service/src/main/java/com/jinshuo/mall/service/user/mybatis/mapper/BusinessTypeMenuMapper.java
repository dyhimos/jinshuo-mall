package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.Menu.BusinessTypeMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface BusinessTypeMenuMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("<script>" +
            "INSERT INTO business_type_menu(" +
            "id,menu_id,business_type_id," +
            "create_date,update_date,remarks) " +
            "VALUES" +
            " <foreach collection='list' item='item' index='index' separator=','> " +
            "(#{item.businessTypeMenuId.id},#{item.menuId},#{item.businessTypeId}," +
            "now(),now(),#{item.remarks})" +
            "</foreach>" +
            "</script>")
    int create(List<BusinessTypeMenu> businessTypeMenu);

    @Results(
            id = "businessTypeMenuResult",
            value = {
                    @Result(property = "businessTypeMenuId.id", column = "id"),
                    @Result(property = "menuId", column = "menu_id"),
                    @Result(property = "businessTypeId", column = "business_type_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM business_type_menu WHERE status = 1 AND  business_type_id=#{businessTypeId} ")
    List<BusinessTypeMenu> queryMyMenu(@Param("businessTypeId") Integer businessTypeId);

    @Update("UPDATE  business_type_menu SET merchant_id=#{merchantId} ," +
            "menu_id=#{menuId}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{merchantId.id}")
    int update(BusinessTypeMenu businessTypeMenu);


    @Update("UPDATE  business_type_menu SET status=4 " +
            " WHERE id = #{businessTypeMenuId}")
    int delete(@Param("businessTypeMenuId") Long businessTypeMenuId);

}
