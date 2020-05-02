package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
public class AddressCreateCmd {


    @ApiModelProperty(value = "所在省")
    @NotNull(message = "所在省不能为空")
    private String province;//所在省

    @ApiModelProperty(value = "所在市")
    @NotNull(message = "所在市不能为空")
    private String city;//所在市

    @ApiModelProperty(value = "所在区县")
    @NotNull(message = "所在区县不能为空")
    private String districts;//所在区县

    @ApiModelProperty(value = "详细地址")
    @NotNull(message = "详细地址不能为空")
    private String address;//详细地址

    @ApiModelProperty(value = "收货人")
    @NotNull(message = "收货人不能为空")
    private String receiver;//收货人

    @ApiModelProperty(value = "联系手机")
    @NotNull(message = "联系手机不能为空")
    private String phone;//联系手机

    @ApiModelProperty(value = "是否默认收货地址 0是 1否")
    @NotNull(message = "是否默认收货地址不能为空")
    private Integer isDefault;//是否默认收货地址 0是 1否
}
