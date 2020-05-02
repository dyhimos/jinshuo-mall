package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 生成微信带参数二维码自定义参数
* @Description:
* @Author:         dongyh
* @CreateDate:     2019/9/18 17:04
* @UpdateUser:     dongyh
* @UpdateDate:     2019/9/18 17:04
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class WxCreateQrcodeCmd {

    @ApiModelProperty(value = "店铺id ")
    private Long shopId;

    @ApiModelProperty(value = "邀请码 ")
    private String inviteCode;

    @ApiModelProperty(value = "当前地址的url")
    private String url;
}
