package com.jinshuo.mall.service.user.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 账户信息dto
 * @author dongyh
 * @Title: WxUserInfoDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class UserAccountDto {
    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邀请码
     */
    private String inviteCode;


    /**
     * 最后一次登录时间
     */
    private Date lastLoginAt;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIpAt;

    /**
     * 登录次数
     */
    private Integer loginCount;

}
