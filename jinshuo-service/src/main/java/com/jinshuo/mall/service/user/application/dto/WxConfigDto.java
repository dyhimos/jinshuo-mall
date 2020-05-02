package com.jinshuo.mall.service.user.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信返回前端信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxConfigDto {
    /**
     * 公众号的 appid
     */
    @ApiModelProperty(value = "appid")
    private String appid;

    /**
     * 服务方的 appid
     */
    @ApiModelProperty(value = "component_appid")
    private String component_appid;


    @ApiModelProperty(value = "type 0:商家 1：渠道商")
    private Integer type;

}
