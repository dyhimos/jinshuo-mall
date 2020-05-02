package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMobileCmd {
    @ApiModelProperty(value = "二次校验数据")
    private GeeCmd gee;

    @ApiModelProperty(value = "手机号码")
    private String mobile;
}
