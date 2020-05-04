package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountStatusEnums;
import com.jinshuo.mall.service.user.application.dto.UserManagerAccountDto;
import com.jinshuo.mall.service.user.application.qry.UserAccountQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname UserAccountMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface UserAccountMapper {

    @InsertProvider(type = UserDynamicSql.class, method = "createUserAccountSql")
    void save(UserAccount userAccount);

    @Results(
            id = "UserAccountResult",
            value = {
                    @Result(property = "userAccountId.id", column = "id"),
                    @Result(property = "nickname", column = "nickname"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "phone", column = "phone"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "inviteCode", column = "invite_code"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "userStatus", column = "user_status",javaType = UserAccountStatusEnums.class,typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("SELECT * FROM user_account WHERE id=#{id}")
    UserAccount findById(UserAccountId userAccountId);


    @ResultMap(value="UserAccountResult")
    @Select("SELECT * FROM user_account WHERE invite_code=#{inviteCode}")
    UserAccount findByInvaildCode(String inviteCode);

    @ResultMap(value="UserAccountResult")
    @Select("SELECT * FROM user_account WHERE username=#{username}")
    UserAccount findByUserName(String username);

    @ResultMap(value="UserAccountResult")
    @Select("SELECT * FROM user_account WHERE phone=#{phone}")
    UserAccount findByPhone(String phone);


    @ResultMap(value="UserAccountResult")
    @Select("SELECT * FROM user_account WHERE phone=#{phone} AND type=#{type}")
    UserAccount findByPhoneAndType(@Param("phone") String phone, @Param("type") Integer type);


    @Update("UPDATE  user_account SET password=#{password} " +
            " WHERE id = #{userAccountId.id} ")
    int updatePassword(UserAccount userAccount);

    @Update("UPDATE  user_account SET nickname=#{nickname},username=#{username},phone=#{phone},type=#{type}," +
            "user_status=#{userStatus.code},password=#{password} " +
            " WHERE id = #{userAccountId.id} ")
    int update(UserAccount userAccount);

    @ResultMap(value="UserAccountResult")
    @Select("SELECT * FROM user_account WHERE username=#{username} AND type=#{type} ")
    List<UserAccount> queryByUserNameAndType(@Param("username") String username, @Param("type") Integer type);


    @Update("UPDATE  user_account SET last_login_at=#{lastLoginAt},login_count=#{loginCount} " +
            " WHERE id = #{userAccountId.id} ")
    int updateLoginLog(UserAccount userAccount);


    /**
     * 查询后台管理账户
     * @param query
     * @return
     */
    @Results(
            id = "managerAccountResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "nickname", column = "nickname"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "roleName", column = "roleName"),
                    @Result(property = "userStatus", column = "user_status",javaType = UserAccountStatusEnums.class,typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("<script>" +
            "SELECT u.id,u.nickname,u.username,u.phone,u.user_status,GROUP_CONCAT(r.name) as roleName from user_account u " +
            " LEFT JOIN user_role ur on u.id = ur.user_id" +
            " LEFT JOIN role r on r.id=ur.role_id " +
            " where 1=1  and u.type =5 " +
            "<if test='nickname != null and nickname !=\"\"'> " +
            "and u.nickname like '%${nickname}%'" +
            "</if>" +
            "<if test='userStatus != null'> " +
            "and u.user_status = #{userStatus}" +
            "</if>" +
            "GROUP BY u.id" +
            "</script>")
    List<UserManagerAccountDto> queryManagerAccount(UserAccountQry query);


    /**
     * 删除用户
     */
    @Delete({"<script>" +
            "delete user_account where id=#{id}"
            + "</script>"})
    void deleteUserAccount(Long id);
}
