package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 功能介绍信息
 * @author dongyh
 * @Title: SignatureInfoDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class HeadImageInfoDto {
    /**
     * 头像 url
     */
    private String head_image_url;

    /**
     * 头像已使用修改次数（本月）
     */
    private Integer modify_used_count;

    /**
     * 头像修改次数总额度（本月）
     */
    private Integer modify_quota;
}
