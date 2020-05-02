package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountStatusEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员账户信息dto
 *
 * @author dongyh
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class UserManagerAccountDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 登录名
     */
    private String username;

    /**
     * 用户名
     */
    private String nickname;


    /**
     * 手机
     */
    private String phone;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 账户状态
     */
    private UserAccountStatusEnums userStatus;


}
