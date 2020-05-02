package com.jinshuo.mall.service.user.application.cmd;

import lombok.Data;

/**
 * Created by 19458 on 2019/11/22.
 */
@Data
public class LoginPasswordCmd {
    private String password;
    private String newPassword;
    private String code;
    private String phone;
}
