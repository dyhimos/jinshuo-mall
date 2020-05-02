package com.jinshuo.mall.service.order.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/8/19.
 */
@Data
public class SupplierDto {
    private String id;//

    @ApiModelProperty(value = "user_id")
    private Long userId;//user_id

    @ApiModelProperty(value = "供应商号码")
    private String supplierCode;//供应商号码

    @ApiModelProperty(value = "所在区")
    private String iconUrl;//供应商图标

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;//供应商名称

    @ApiModelProperty(value = "供应商类型")
    private Integer supplierType;//供应商类型

    @ApiModelProperty(value = "联系人")
    private String linkMan;//联系人

    @ApiModelProperty(value = "联系电话")
    private String phoneNumber;//联系电话

    @ApiModelProperty(value = "所在省")
    private String province;//所在省

    @ApiModelProperty(value = "所在市")
    private String city;//所在市

    @ApiModelProperty(value = "所在区")
    private String disc;//所在区

    @ApiModelProperty(value = "详情地址")
    private String address;//详情地址

    @ApiModelProperty(value = "供应商介绍1、平台商家；2、度假酒店；3、景点票类；4、亲子乐园；4、特产商家")
    private String desc;//供应商介绍1、平台商家；2、度假酒店；3、景点票类；4、亲子乐园；4、特产商家

    @ApiModelProperty(value = "供应商状态状态：0冻结，1正常")
    private Integer supplierStatus;//供应商状态状态：0冻结，1正常

    @ApiModelProperty(value = "是否开启登陆  0开启，1不开启")
    private Integer loginFlag;
}
