package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by 19458 on 2019/9/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto  {
    private String id;
    private String name;
    private Integer type;

    @ApiModelProperty(value = "店铺头像 ")
    private String logo;

    @ApiModelProperty(value = "简介 ")
    private String introduce;

    @ApiModelProperty(value = "客服电话 ")
    private String customerTel;

    @ApiModelProperty(value = "营业时间：1全天 2：自定义 ")
    private Integer workType;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "开始时间")
    private Date starTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
