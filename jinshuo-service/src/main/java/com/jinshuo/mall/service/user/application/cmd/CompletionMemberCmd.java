package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/9/29.
 */
@Data
public class CompletionMemberCmd {

    @ApiModelProperty(value = "id")
    public Long userAccountId;

    @ApiModelProperty(value = "姓名")
    private String surname;

    @ApiModelProperty(value = "身份证账户")
    private String idCard;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "支付渠道1、微信支付 3、支付宝账号")
    private Integer payChannel;      // 支付渠道1、微信支付 3、支付宝账号

    @ApiModelProperty(value = "支付宝账号")
    private String payNo;           // 账号 (微信号，支付宝账号)

    @ApiModelProperty(value = "微信号")
    private String wxNo;           // 账号 (微信号，支付宝账号)

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "客户昵称")
    private String nickname;
}
