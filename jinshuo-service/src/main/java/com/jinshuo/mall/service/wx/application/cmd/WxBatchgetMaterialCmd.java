package com.jinshuo.mall.service.wx.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 获取公众平台资源
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
public class WxBatchgetMaterialCmd {

    @ApiModelProperty(value = "素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） ")
    private String type;

    @ApiModelProperty(value = "从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 ")
    private Integer offset;

    @ApiModelProperty(value = "返回素材的数量，取值在1到20之间 ")
    private Integer count;
}
