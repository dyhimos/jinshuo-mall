package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/7/23.
 */
@Data
public class PageQuery {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;
}
