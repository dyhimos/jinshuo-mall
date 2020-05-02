package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/9/10.
 */
@Data
public class LatticeQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty(value = "店铺id")
    public Long shopId;

}
