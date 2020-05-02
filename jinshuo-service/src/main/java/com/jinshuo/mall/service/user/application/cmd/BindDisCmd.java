package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @Description:    手动绑定分销员
* @Author:         dongyh
* @CreateDate:     2019/8/29 17:06
* @UpdateUser:     dongyh
* @UpdateDate:     2019/8/29 17:06
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class BindDisCmd {

    @ApiModelProperty(value = "邀请码")
    @NotNull(message = "邀请码不能为空")
    private String inviteCode;
}
