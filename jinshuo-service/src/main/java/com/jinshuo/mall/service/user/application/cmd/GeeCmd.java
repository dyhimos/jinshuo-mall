package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/8/16.
 */
@Data
public class GeeCmd {

    @ApiModelProperty(value = "极验验证二次验证表单数据 chllenge")
    private String geetest_challenge;

    @ApiModelProperty(value = "极验验证二次验证表单数据 validate ")
    private String geetest_validate;

    @ApiModelProperty(value = "极验验证二次验证表单数据 seccode ")
    private String geetest_seccode;

}
