package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 账户详情dto
 * @author dongyh
 * @Title: WxUserInfoDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDetailDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "登录用户名")
    private String username;

    @ApiModelProperty(value = "用户名")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "角色ids")
    private List<String> roleIds;

    @ApiModelProperty(value = "菜单ids")
    private List<String> menuIds;
}
