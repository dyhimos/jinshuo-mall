package com.jinshuo.mall.domain.user.model.userAccountPlatform;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.SpringUtil;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dongyh
 * @Classname AccountPlatform
 * @Description 微信账户
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountPlatform extends IdentifiedEntity {

    /**
     * 微信账户Id
     */
    private UserAccountPlatformId userAccountPlatformId;


    /**
     * 账户编号
     */
    private UserAccountId userAccountId;

    /**
     * 用户账户信息
     */
    private UserAccount userAccount;

    /**
     * 会员信息
     */
    private Member member;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 平台类型 1:wechat
     */
    private String type;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 微信openid
     */
    private String unionid;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 性别
     */
    private SexEnums sex;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺类别
     */
    private String shopType;


    /**
     * 判断用户是否存在
     *
     * @param openid
     * @return
     */
    public static Boolean chekIsExit(String openid, Long shopId) {
        UserAccountPlatform userAccountPlatform = SpringUtil.getBean(IsUserAccountPlatformRepo.class).findByOpenid(openid, shopId);
        if (userAccountPlatform == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断登录方式的unionId是否存在
     *
     * @param unionId
     * @return
     */
    public static Boolean chekIsExitUnionId(String unionId, Long shopId) {
        List<UserAccountPlatform> userAccountPlatformList = SpringUtil.getBean(IsUserAccountPlatformRepo.class).findByUnionId(unionId, shopId);
        if (userAccountPlatformList.size() > 0) {
            return false;
        }
        return true;
    }


    /**
     * 保存信息
     *
     * @param userAccountId
     * @param nickname
     * @param type
     * @param openid
     * @param avatar
     * @param sex
     * @return
     */
    public UserAccountPlatform save(UserAccountPlatformId userAccountPlatformId, UserAccountId userAccountId, String nickname, String type, String openid, String unionid, String avatar, SexEnums sex) {
        preInsert();
        this.userAccountPlatformId = userAccountPlatformId;
        this.userAccountId = userAccountId;
        this.nickname = nickname;
        this.type = type;
        this.openid = openid;
        this.unionid = unionid;
        this.avatar = avatar;
        this.sex = sex;
        return this;
    }
}
