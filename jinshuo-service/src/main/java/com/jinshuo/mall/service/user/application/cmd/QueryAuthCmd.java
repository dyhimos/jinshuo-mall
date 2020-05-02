package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @Description:  获取授权信所需参数
* @Author:         dongyh
* @CreateDate:     2019/10/16 10:26
* @UpdateUser:     dongyh
* @UpdateDate:     2019/10/16 10:26
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class QueryAuthCmd {

    /**
     * 授权码, 会在授权成功时返回给第三方平台
     */
    @ApiModelProperty(value = "授权码")
    @NotNull(message = "授权码不能为空")
    private String authorizationCode;

    @ApiModelProperty(value = "店铺id")
    @NotNull(message = "店铺id为空")
    private Long shopId;
}
