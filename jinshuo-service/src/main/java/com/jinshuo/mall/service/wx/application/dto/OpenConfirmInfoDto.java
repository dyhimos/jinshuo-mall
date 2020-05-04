package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 第三方平台授权信息权限确认信息
 * @author dongyh
 * @Title: OpenConfirmInfoDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 15:59
 */
@Data
public class OpenConfirmInfoDto {

    /**
     * 需要确认
     */
    private Integer need_confirm;

    /**
     * 可以确认
     */
    private Integer can_confirm;

    /**
     * 已经确认
     */
    private Integer already_confirm;
}
