package com.jinshuo.mall.domain.user.model.userAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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


    /**
     * 检查登录信息是否正确
     *
     * @param username
     */
    public static UserAccount checkLoginInfoLogin(String username, String password, Integer type) {
       /* List<UserAccount> userAccountList = SpringUtil.getBean(UserAccountRepo.class).findByUserNameAndType(username,type);
        if(userAccountList.size()==0){
            throw new UcBizException(UcReturnCode.UC200009.getMsg(),UcReturnCode.UC200009.getCode());
        }
        UserAccount userAccount = userAccountList.get(0);
        if(StringUtils.isNotBlank(password)){
            if(!BPwdEncoderUtils.matches(password,userAccount.getPassword())){
                throw new UcBizException(UcReturnCode.UC200010.getMsg(),UcReturnCode.UC200010.getCode());
            }
        }
        return userAccount;*/
        return null;
    }

    /**
     * 检查登录信息是否正确
     *
     * @param username
     */
    public static UserAccount checkLoginInfo(String username, String password) {
        /*UserAccount userAccount = SpringUtil.getBean(UserAccountRepo.class).findByUserName(username);
        if(userAccount==null){
            throw new UcBizException(UcReturnCode.UC200009.getMsg(),UcReturnCode.UC200009.getCode());
        }
        if(StringUtils.isNotBlank(password)){
            if(!BPwdEncoderUtils.matches(password,userAccount.getPassword())){
                throw new UcBizException(UcReturnCode.UC200010.getMsg(),UcReturnCode.UC200010.getCode());
            }
        }
        return userAccount;*/
        return null;
    }

    public List<UserAccount> checkUserName(String userName, Integer type) {
        //return SpringUtil.getBean(UserAccountRepo.class).findByUserNameAndType(userName, type);
        return null;
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