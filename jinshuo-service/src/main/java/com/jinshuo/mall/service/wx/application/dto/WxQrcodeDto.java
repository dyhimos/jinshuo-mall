package com.jinshuo.mall.service.wx.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信生成二维码返回信息
* @Description:
* @Author:         mgh
* @CreateDate:     2019/9/18 17:04
* @UpdateUser:     mgh
* @UpdateDate:     2019/9/18 17:04
* @UpdateRemark:
* @Version:        1.0
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxQrcodeDto {

    /**
     * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    private String ticket;


    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private Long expire_seconds;


    /**
     *  二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    private String url;
}
