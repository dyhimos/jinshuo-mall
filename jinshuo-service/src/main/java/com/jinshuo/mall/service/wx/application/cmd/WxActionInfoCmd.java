package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Builder;
import lombok.Data;

/**
 * 二维码详细信息
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
public class WxActionInfoCmd {

    /**
     * 二维码详细信息
     */
    private WxSenceInfoCmd scene;
}
