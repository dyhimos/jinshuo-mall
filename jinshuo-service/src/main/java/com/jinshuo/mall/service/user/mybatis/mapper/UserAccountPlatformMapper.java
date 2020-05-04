package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.SexEnums;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatform;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname GoodsOrderMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface UserAccountPlatformMapper {

    @InsertProvider(type = UserDynamicSql.class, method = "createUserAccountPlatformSql")
    void save(UserAccountPlatform userAccountPlatform);

    @ResultMap(value = "UserAccountPlatformResult")
    @Select("SELECT * FROM user_account_platform WHERE id=#{id}")
    UserAccountPlatform findById(String id);

    @ResultMap(value = "UserAccountPlatformResult")
    @Select("SELECT * FROM user_account_platform WHERE openid=#{openid} and shop_id=#{shopId}")
    UserAccountPlatform findByOpenid(@Param("openid") String openid, @Param("shopId") Long shopId);

    @ResultMap(value = "UserAccountPlatformResult")
    @Select("SELECT * FROM user_account_platform WHERE user_id=#{id}")
    UserAccountPlatform findByUserId(UserAccountId userAccountId);


    /**
     * 更新微信信息
     *
     * @param userAccountPlatform
     */
    @UpdateProvider(type = UserDynamicSql.class, method = "updateUserAccountPlatformSql")
    void update(UserAccountPlatform userAccountPlatform);


    @Results(
            id = "UserAccountPlatformResult",
            value = {
                    @Result(property = "userAccountPlatformId.id", column = "id"),
                    @Result(property = "userAccountId.id", column = "user_id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "nickname", column = "nickname"),
                    @Result(property = "openid", column = "openid"),
                    @Result(property = "unionid", column = "unionid"),
                    @Result(property = "avatar", column = "avatar"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(column = "user_id", property = "userAccount", javaType = UserAccount.class,
                            one = @One(select = "com.ym.wool.uc.infra.mybatis.mapper.UserAccountMapper.findById")),
                    @Result(column = "user_id", property = "member", javaType = Member.class,
                            one = @One(select = "com.ym.wool.uc.infra.mybatis.mapper.MemberMapper.queryByUserId")),
                    @Result(property = "sex", column = "sex", javaType = SexEnums.class, typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("SELECT * FROM user_account_platform WHERE unionid=#{unionId} and shop_id=#{shopId}")
    List<UserAccountPlatform> findByUnionId(@Param("unionId") String unionId, @Param("shopId") Long shopId);

    @ResultType(String.class)
    @Select("SELECT openid FROM user_account_platform where shop_id=#{shopId}")
    List<String> getExistOpenIds(Long shopId);
}
