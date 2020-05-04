package com.jinshuo.mall.service.wx.application.cmd;

import lombok.Data;

/**
 * 场景
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
public class WxSenceInfoCmd {

    /**
     * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    private Long scene_id;

    /**
     * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    private String scene_str;


    /**
     * 二维码场景为long
     *
     * @param scene_id
     * @return
     */
    public WxSenceInfoCmd(Long scene_id) {
        this.scene_id = scene_id;
    }

    /**
     * 二维码场景为String类型
     *
     * @param scene_str
     * @return
     */
    public WxSenceInfoCmd(String scene_str) {
        this.scene_str = scene_str;
    }
}
