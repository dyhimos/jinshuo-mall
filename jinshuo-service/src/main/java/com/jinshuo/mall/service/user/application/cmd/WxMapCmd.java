package com.jinshuo.mall.service.user.application.cmd;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Classname WxGzptLoginCmd
 * @Description 微信公众平台登录
 * @Date 2019/6/16 :01
 * @Created by dongyh
 * @author dongyh
 */
@Data
@Accessors(chain = true)
public class WxMapCmd {

    @NotNull(message = "位置坐标")
    private String location;
}
