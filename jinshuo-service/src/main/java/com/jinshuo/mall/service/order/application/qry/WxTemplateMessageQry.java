package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信模板消息
 * WxTemplateMessageQry
 */
@Data
public class WxTemplateMessageQry {
    @ApiModelProperty(value = "模板类型")
    private Integer templateType;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;
}
