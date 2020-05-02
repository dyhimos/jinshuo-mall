package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @Description:    分销升级所需参数
* @Author:         dongyh
* @CreateDate:     2019/10/22 9:41
* @UpdateUser:     dongyh
* @UpdateDate:     2019/10/22 9:41
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class UpdisLvCmd {

    /*@ApiModelProperty(value = "会员id ")
    private List<Long> memberIds;*/
    @ApiModelProperty(value = "会员id ")
    private Long memberId;


    @ApiModelProperty(value = "升级 1：小麦客 0 大麦客")
    @NotNull(message = "升级 1：小麦客 0 大麦客")
    private String lv;



}
