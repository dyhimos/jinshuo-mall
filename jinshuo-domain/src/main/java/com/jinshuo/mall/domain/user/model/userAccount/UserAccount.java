package com.jinshuo.mall.domain.user.model.userAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dongyh
 * @Classname AccountUser
 * @Description 用户账户
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    public final static Integer supplierType = 4;
    /**
     * 账户ID
     */
    private UserAccountId userAccountId;

    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;

    /**
     * 账户类型 1：普通会员 2：商家 3：渠道商 4:供应商登录（核销员）5：系统人员
     */
    private Integer type;


    /**
     * 用户名
     */
    private String nickname;

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
     * 商铺id
     */
    private Long merchantId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 创建ip
     */
    private String createIpAt;

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

    /**
     * 账户状态
     */
    private UserAccountStatusEnums userStatus;

    public UserAccount save(UserAccountId userAccountId, String username, String password, Integer type, String nickname, String phone, String email, String inviteCode, Long shopId, String createIpAt, Integer loginCount, UserAccountStatusEnums userStatus) {
        this.userAccountId = userAccountId;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.inviteCode = inviteCode;
        this.shopId = shopId;
        this.createIpAt = createIpAt;
        this.loginCount = loginCount;
        this.userStatus = userStatus;
        return this;
    }

    public UserAccount loginLog() {
        if (null == this.getLoginCount()) {
            this.loginCount = 1;
        } else {
            this.loginCount += 1;
        }
        this.lastLoginAt = new Date();
        return this;
    }
}
