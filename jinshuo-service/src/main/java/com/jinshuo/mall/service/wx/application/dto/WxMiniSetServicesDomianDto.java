package com.jinshuo.mall.service.wx.application.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 代小程序实现业务设置服务器域名
* @Description:
* @Author:         WxMiniSetServicesDomianCmd
* @CreateDate:     2019/9/18 17:04
* @UpdateUser:     mgh
* @UpdateDate:     2019/9/18 17:04
* @UpdateRemark:
* @Version:        1.0
*/
@Data
@Builder
public class WxMiniSetServicesDomianDto {

    /**
     * 	返回码
     */
   private String errcode;


    /**
     * 错误信息
     */
    private String errmsg;


    /**
     *  request 合法域名；
     */
    private String[] requestdomain;

    /**
     *  socket 合法域名；
     */
    private String[] wsrequestdomain;


    /**
     *  uploadFile 合法域名；
     */
    private String[] uploaddomain;


    /**
     *  downloadFile 合法域名；
     */
    private String[] downloaddomain;
}
