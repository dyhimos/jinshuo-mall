package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/7/19.
 */
@Data
public class AlbumQry {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "spuId")
    private Long spuId;

    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @ApiModelProperty(value = "媒体类型")
    private Integer type;
}
