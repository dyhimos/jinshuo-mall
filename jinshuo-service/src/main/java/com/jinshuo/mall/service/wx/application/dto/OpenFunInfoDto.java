package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

import java.util.List;

/**
 * 第三方平台授权权限列表
 * @author dongyh
 * @Title: OpenPlatformAuthInfo
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/14 15:59
 */
@Data
public class OpenFunInfoDto {
    /**
     * 权限结合范围
     */
    private List<OpenFuncscopeCategoryDto> funcscope_category;

    private List<OpenConfirmInfoDto> confirm_info;

}
