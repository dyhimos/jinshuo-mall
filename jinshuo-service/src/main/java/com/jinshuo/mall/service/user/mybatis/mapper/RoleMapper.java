package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.role.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface RoleMapper {

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO role(" +
            "id,shop_id,code,name," +
            "create_date,update_date,remarks,status,version) " +
            "VALUES(" +
            "#{roleId.id},#{shopId.id},#{code},#{name}," +
            "#{createDate},#{updateDate},#{remarks},#{status.code},#{version})")
    int save(Role role);

    /**
     * 查询角色列表
     *
     * @param role
     * @return
     */
    @Results(
            id = "roleResult",
            value = {
                    @Result(property = "roleId.id", column = "id"),
                    @Result(property = "shopId.id", column = "shop_id"),
                    @Result(property = "code", column = "code"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @SelectProvider(type = UserDynamicSql.class, method = "queryRole")
    List<Role> queryRole(Role role);

    @ResultMap(value = "roleResult")
    @Select("SELECT * from  role where id=#{id} and status=1")
    Role findById(Long id);


    @Update("<script>" +
            "update  role set " +
            " name = #{name}," +
            " code = #{code}" +
            "where id= #{roleId.id} " +
            "</script>")
    int update(Role role);

    @Update("UPDATE  role SET status=4 " +
            " WHERE id = #{id} ")
    int delete(@Param("id") Long id);


    /**
     * 新增用户店铺角色中间表数据
     *
     * @param roles
     */
    @Insert({"<script>" +
            "INSERT INTO user_role(user_id,role_id) "
            + "VALUES"
            + "<foreach  collection = 'roles' item = 'roleId'  index ='index' separator=','>"
            + "(#{userId},#{roleId}) "
            + "</foreach>"
            + "</script>"})
    void saveUserShopRole(@Param("roles") List<Long> roles, @Param("userId") Long userId);


    /**
     * 获取用户的角色id列表
     *
     * @param userId
     * @return
     */
    @ResultType(Long.class)
    @Select("SELECT role_id from  user_role where user_id=#{userId}")
    List<Long> getUserRoleIdList(Long userId);


    /**
     * 删除中间表数据
     *
     * @param roleIds
     * @param userId
     */
    @Delete({"<script>" +
            "delete user_role where user_id=#{userId} and role_id in"
            + "<foreach  collection = 'roleIds' item = 'roleId'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{roleId} "
            + "</foreach>"
            + "</script>"})
    void deleteUserRole(@Param("roleIds") List<Long> roleIds, @Param("userId") Long userId);


    /**
     * 新增用户店铺角色菜单中间表数据
     *
     * @param menus
     */
    @Insert({"<script>" +
            "INSERT INTO user_shop_menu(user_id,shop_id,menu_id) "
            + "VALUES"
            + "<foreach  collection = 'menus' item = 'menuId'  index ='index' separator=','>"
            + "(#{userId},#{shopId},#{menuId}) "
            + "</foreach>"
            + "</script>"})
    void saveUserShopMenu(@Param("menus") List<Long> menus, @Param("userId") Long userId, @Param("shopId") Long shopId);


    /**
     * 获取用户的角色id李彪
     *
     * @param userId
     * @return
     */
    @ResultType(Long.class)
    @Select("SELECT menu_id from  user_shop_menu where user_id=#{userId} and shop_id=#{shopId}")
    List<Long> getUserMenuIdList(@Param("userId") Long userId, @Param("shopId") Long shopId);


    /**
     * 删除中间表数据
     *
     * @param menuIdList
     * @param userId
     */
    @Delete({"<script>" +
            "delete user_shop_menu where user_id=#{userId} and shop_id=#{shopId} and menu_id in"
            + "<foreach  collection = 'menuIdList' item = 'menuId'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{menuId} "
            + "</foreach>"
            + "</script>"})
    void deleteUserMenu(@Param("menuIdList") List<Long> menuIdList, @Param("userId") Long userId, @Param("shopId") Long shopId);


    /**
     * 保存角色菜单中间表
     *
     * @param menus
     * @param roleId
     * @return
     */
    @Insert({"<script>" +
            "INSERT INTO role_menu(role_id,menu_id) "
            + "VALUES"
            + "<foreach  collection = 'menus' item = 'menuId'  index ='index' separator=','>"
            + "(#{roleId},#{menuId}) "
            + "</foreach>"
            + "</script>"})
    int saveRoleMenu(@Param("menus") List<Long> menus, @Param("roleId") Long roleId);


    /**
     * 删除角色菜单中间表数据
     *
     * @param menuIdList
     * @param roleId
     */
    @Delete({"<script>" +
            "delete role_menu where roleId=#{roleId} and menu_id in"
            + "<foreach  collection = 'menuIdList' item = 'menuId'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{menuId} "
            + "</foreach>"
            + "</script>"})
    void deleteRoleMenu(@Param("menuIdList") List<Long> menuIdList, @Param("roleId") Long roleId);
}
