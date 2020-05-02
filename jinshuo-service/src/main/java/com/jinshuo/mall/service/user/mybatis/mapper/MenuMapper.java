package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.Menu.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface MenuMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO menu(" +
            "id,type,menu_type,name,pid,desc,code,sort," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{menuId.id},#{type},#{menuType},#{name},#{pid},#{desc},#{code},#{sort}," +
            "now(),now(),#{remarks})")
    int create(Menu menu);

    @Results(
            id = "merchantMenuResult",
            value = {
                    @Result(property = "menuId.id", column = "id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "menuType", column = "menu_type"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "pid", column = "pid"),
                    @Result(property = "desc", column = "desc"),
                    @Result(property = "code", column = "code"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("<script>" +
            "SELECT * FROM menu WHERE status = 1  " +
            "<if test='type != null'> " +
            "AND type = #{type} " +
            "</if>" +
            "<if test='pid != null'> " +
            "AND pid = #{pid} " +
            "</if>" +
            "ORDER BY sort DESC " +
            "</script>")
    List<Menu> queryAllMenu(Menu menu);

    @Update("UPDATE  menu SET type=#{type} ," +
            "name=#{name},pid=#{pid},desc=#{desc},code=#{code},sort=#{sort}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{menuId.id}")
    int update(Menu menu);


    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Update("UPDATE  menu SET status=4  WHERE id = #{id}")
    int delete(Long id);

    /**
     * 根据功能id查询菜单列表
     * @param functionIds
     * @return
     */
    @ResultMap("merchantMenuResult")
    @Select("<script>" +
            "SELECT a.* FROM menu a left join function_menu b on a.id=b.menu_id WHERE b.fun_id in "
            + "<foreach  collection = 'functionIds' item = 'functionId'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{functionId} "
            + "</foreach>"
            +"</script>")
    List<Menu> queryMenuByFunctionId(@Param("functionIds") List<Long> functionIds);


    /**
     * 根据角色id查询菜单列表
     * @param roleIds 角色ID列表
     * @return
     */
    @ResultMap("merchantMenuResult")
    @Select("<script>" +
            "SELECT a.* FROM menu a left join role_menu b on a.id=b.menu_id WHERE b.role_id in "
            + "<foreach  collection = 'roleIds' item = 'roleId'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{roleId} "
            + "</foreach>"
            +"</script>")
    List<Menu> queryMenuByRoleId(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据店铺和用户查询菜单
     * @param shopId
     * @param userId
     * @return
     */

   /* @ResultMap("merchantMenuResult")
    @Select("<script>" +
            "SELECT a.* FROM menu a " +
            "left join role_menu b on a.id=b.menu_id " +
            "left join user_shop_role c on b.role_id =c.role_id " +
            "WHERE c.user_id =#{userId} and c.shop_id =#{shopId} "+
            "</script>")
    List<Menu> queryMenuByShop(@Param("shopId")Long shopId,@Param("userId")Long userId);*/
    @ResultMap("merchantMenuResult")
    @Select("<script>" +
            "SELECT a.* FROM menu a " +
            "left join user_shop_menu b on a.id=b.menu_id " +
            "WHERE b.user_id =#{userId} and b.shop_id =#{shopId} "+
            "</script>")
    List<Menu> queryMenuByShop(@Param("shopId") Long shopId, @Param("userId") Long userId);

}
