package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/8/19.
 */
@Data
public class SupplierCmd  {

    @ApiModelProperty(value = "会员编号 ")
    //@NotNull(message = "会员编号不能为空")
    private Long id;//

    @ApiModelProperty(value = "会员编号 ")
    //@NotNull(message = "会员编号不能为空")
    private Long userId;//user_id

    @ApiModelProperty(value = "供应商代码 ")
    //@NotNull(message = "供应商代码不能为空")
    private String supplierCode;//供应商号码

    @ApiModelProperty(value = "供应商图标 ")
    //@NotNull(message = "供应商图标不能为空")
    private String iconUrl;//供应商图标

    @ApiModelProperty(value = "供应商名称 ")
    @NotNull(message = "供应商名称不能为空")
    private String supplierName;//供应商名称

    @ApiModelProperty(value = "供应商类型 ")
    //@NotNull(message = "供应商类型不能为空")
    private Integer supplierType;//供应商类型

    @ApiModelProperty(value = "联系人 ")
    @NotNull(message = "联系人不能为空")
    private String linkMan;//联系人

    @ApiModelProperty(value = "联系电话 ")
    @NotNull(message = "联系电话不能为空")
    private String phoneNumber;//联系电话

    @ApiModelProperty(value = "所在省 ")
    @NotNull(message = "所在省不能为空")
    private String province;//所在省

    @ApiModelProperty(value = "所在市 ")
    @NotNull(message = "所在市不能为空")
    private String city;//所在市

    @ApiModelProperty(value = "所在区 ")
    @NotNull(message = "所在区不能为空")
    private String disc;//所在区

    @ApiModelProperty(value = "详情地址 ")
    @NotNull(message = "详情地址不能为空")
    private String address;//详情地址

    @ApiModelProperty(value = "供应商介绍 ")
    //@NotNull(message = "供应商介绍不能为空")
    private String desc;//供应商介绍1、平台商家；2、度假酒店；3、景点票类；4、亲子乐园；4、特产商家

    @ApiModelProperty(value = "供应商状态状态 ")
    //@NotNull(message = "会员编号不能为空")
    private Integer supplierStatus;//供应商状态状态：1冻结，0正常

    private Long shopId;

    @ApiModelProperty(value = "是否开启登陆 0-> 可登陆  1->不可登陆")
    private Integer loginFlag; // 0-> 可登陆  1->不可登陆
}
