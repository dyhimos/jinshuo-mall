package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;

/**
 * 小程序审核参数
 *
 * @Description:
 * @Author: mgh
 * @CreateDate: 2019/9/18 17:04
 * @UpdateUser: mgh
 * @UpdateDate: 2019/9/18 17:04
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Builder
public class WxMiniCommitAuditCmd {

    /**
     * 接口调用凭证(必填)
     */
    private String access_token;


    /**
     * (必填)审核项列表（至少填写 1 项，至多填写 5 项）
     */
    private Array[] item_list;


    /**
     * 反馈内容，至多 200 字
     */
    private String feedback_info;

    /**
     * 用 | 分割的 media_id 列表，至多 5 张图片, 可以通过新增临时素材接口上传而得到
     */
    private String feedback_stuff;
}
