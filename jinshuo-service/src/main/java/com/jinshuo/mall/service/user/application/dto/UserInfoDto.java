package com.jinshuo.mall.service.user.application.dto;

import com.jinshuo.mall.domain.user.model.userAccountPlatform.SexEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台查询用户返回信息
 * @author dongyh
 * @Title: WxUserInfoDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class UserInfoDto {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String openid;

    /**
     * 性别
     */
    private SexEnums sex;

    /**
     * 用户账户信息
     */
    private UserAccountDto userAccount;

    private MemberInfoDto member;

}
