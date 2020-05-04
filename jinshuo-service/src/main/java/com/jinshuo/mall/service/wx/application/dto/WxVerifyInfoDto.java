package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 微信认证信息
 * @author dongyh
 * @Title: WxVerifyInfoDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class WxVerifyInfoDto {
    /**
     * 是否资质认证，若是，拥有微信认证相关的权限。
     */
    private Boolean qualification_verify;

    /**
     * 是否名称认证
     */
    private Boolean naming_verify;

    /**
     * 是否需要年审（qualification_verify == true 时才有该字段）
     */
    private String annual_review;

    /**
     * 年审开始时间，时间戳（qualification_verify == true 时才有该字段）
     */
    private Long annual_review_begin_time;

    /**
     * 年审截止时间，时间戳（qualification_verify == true 时才有该字段）
     */
    private Long annual_review_end_time;
}
