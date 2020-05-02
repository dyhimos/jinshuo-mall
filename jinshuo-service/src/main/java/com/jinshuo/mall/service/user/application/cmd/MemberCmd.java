package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @Description:    更新会员为分销用户
* @Author:         dongyh
* @CreateDate:     2019/8/20 11:40
* @UpdateUser:     dongyh
* @UpdateDate:     2019/8/20 11:40
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class MemberCmd {

    @ApiModelProperty(value = "会员编号 ")
    @NotNull(message = "会员编号不能为空")
    private Long userId;

    @ApiModelProperty(value = "父节点")
    private Long pId;

}
