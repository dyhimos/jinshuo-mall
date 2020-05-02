package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/11/22.
 */
@Data
public class LogQry {
    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "spuId")
    private Long spuId;
}
