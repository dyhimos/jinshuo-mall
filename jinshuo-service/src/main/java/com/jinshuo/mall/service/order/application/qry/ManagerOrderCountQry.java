package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by 19458 on 2019/12/13.
 */
@Data
public class ManagerOrderCountQry {
    @ApiModelProperty(value = " shopId")
    private Long shopId;

    @ApiModelProperty(value = " 1:今天 2:昨天 3：七日 4：时间范围")
    private Integer qryType;

    @ApiModelProperty(value = " 开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
