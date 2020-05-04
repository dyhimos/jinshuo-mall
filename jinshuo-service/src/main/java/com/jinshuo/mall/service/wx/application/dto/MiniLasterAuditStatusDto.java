package com.jinshuo.mall.service.wx.application.dto;

import lombok.Data;

/**
 * 通过网页授权获取access_token
 * @author dongyh
 * @Title: ComponentAccessTokenDto
 * @ProjectName jinshuo-mall
 * @Description: TODO
 * @date 2019/10/15 15:32
 */
@Data
public class MiniLasterAuditStatusDto {
    /**
     * 返回码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 最新的审核 ID
     */
    private String auditid;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 当审核被拒绝时，返回的拒绝原因
     */
    private String reason;

    /**
     * 当审核被拒绝时，会返回审核失败的小程序截图示例。
     * 用 | 分隔的 media_id 的列表，可通过获取永久素材接口拉取截图内容
     */
    private String ScreenShot;
}
