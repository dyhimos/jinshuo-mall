package com.jinshuo.mall.service.order.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/** 快递进度查询
* @Description:
* @Author:         dongyh
* @CreateDate:     2019/10/23 15:29
* @UpdateUser:     dongyh
* @UpdateDate:     2019/10/23 15:29
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class ExpressScheduleCmd {
    @ApiModelProperty(value = "skuId")
    @NotNull(message = "快递单号")
    private String expressNo;

    @ApiModelProperty(value = "快递编码")
    @NotNull(message = "快递编码")
    private String expressCode;
}
