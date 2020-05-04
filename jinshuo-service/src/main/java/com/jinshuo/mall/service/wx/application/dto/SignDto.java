package com.jinshuo.mall.service.wx.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname SignDto
 * @Description JSSDK签名dto
 * @Date 2019/6/28 9:29
 * @Created by mgh
 * @author  mgh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignDto {

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "时间戳")
    private String timestamp;

    @ApiModelProperty(value = "生成签名随机字符串")
    private String nonceStr;

    @ApiModelProperty(value = "签名")
    private String signature;

}
