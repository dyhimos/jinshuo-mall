package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 19458 on 2019/10/17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantLoginInfoDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String avatar;
    private String status;
    private String telephone;
    private String lastLoginIp;
    private String lastLoginTime;
    private String creatorId;
    private String createTime;
    private String deleted;
    private String roleId;
    private String lang;
    private String token;

    private List<ShopDto> shops;
}
