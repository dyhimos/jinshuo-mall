package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.merchant.MerchantMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface MerchantMenuMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO merchant_menu(" +
            "id,merchant_id,role_id,menu_id,shop_id," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{merchantMenuId.id},#{merchantId},#{roleId},#{menuId},#{shopId}," +
            "now(),now(),#{remarks})")
    int create(MerchantMenu merchantMenu);

    @Insert("INSERT INTO merchant_menu(" +
            "id,merchant_id,role_id,menu_id,shop_id," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{merchantMenuId.id},#{merchantId},#{roleId},#{menuId},#{shopId}," +
            "now(),now(),#{remarks})")
    int save(List<MerchantMenu> list);

    @Results(
            id = "merchantMenuResult",
            value = {
                    @Result(property = "merchantMenuId.id", column = "id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "merchantId", column = "merchant_id"),
                    @Result(property = "roleId", column = "role_id"),
                    @Result(property = "menuId", column = "menu_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM merchant_menu WHERE status = 1 AND  shop_id=#{shopId} ")
    List<MerchantMenu> queryMyMenu(@Param("shopId") Long shopId);


    /**
     * 根据角色id查询对应的菜单
     * @param roleId 角色id
     * @return
     */
    @ResultMap("merchantMenuResult")
    @Select("SELECT * FROM merchant_menu WHERE status = 1 AND  role_id=#{roleId} ")
    List<MerchantMenu> findByRoleId(@Param("roleId") Long roleId);

    @Update("UPDATE  merchant_menu SET merchant_id=#{merchantId} ," +
            "menu_id=#{menuId}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{merchantId.id}")
    int update(MerchantMenu merchantMenu);


    @Delete({"<script>" +
            "delete merchant_menu where role_id=#{roleId} and menu_id in"
            + "<foreach  collection = 'menuIdList' item = 'menuId'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{menuId} "
            + "</foreach>"
            + "</script>"})
    int delete(@Param("menuIdList") List<Long> menuIdList, @Param("roleId") Long roleId);

}
