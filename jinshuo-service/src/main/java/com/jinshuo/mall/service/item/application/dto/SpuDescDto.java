package com.jinshuo.mall.service.item.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/7/23.
 */
@Data
public class SpuDescDto {
    private String id;

    @ApiModelProperty(value = "spuId")
    private String spuId;

    @ApiModelProperty(value = "pc图文详情")
    private String pcDesc;

    @ApiModelProperty(value = "mobile图文详情")
    private String mobileDesc;

    @ApiModelProperty(value = "预定须知")
    private String bookingNotes;


}
