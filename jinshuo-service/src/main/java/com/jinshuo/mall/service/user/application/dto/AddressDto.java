package com.jinshuo.mall.service.user.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
public class AddressDto {

    private String id;//编号

    @ApiModelProperty(value = "会员编号")
    private String memId;//会员编号

    @ApiModelProperty(value = "所在省")
    private String province;//所在省

    @ApiModelProperty(value = "所在市")
    private String city;//所在市

    @ApiModelProperty(value = "所在区县")
    private String districts;//所在区县

    @ApiModelProperty(value = "详细地址")
    private String address;//详细地址

    @ApiModelProperty(value = "收货人")
    private String receiver;//收货人

    @ApiModelProperty(value = "联系手机")
    private String phone;//联系手机

    @ApiModelProperty(value = "是否默认收货地址 0是 1否")
    private Integer isDefault;//是否默认收货地址 0是 1否
}
