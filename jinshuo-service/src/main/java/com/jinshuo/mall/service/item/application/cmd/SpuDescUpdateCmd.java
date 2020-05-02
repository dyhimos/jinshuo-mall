package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 19458 on 2019/7/18.
 */
@Data
public class SpuDescUpdateCmd implements Serializable {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空!")
    private Long id;

/*    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id不能为空!")
    private Long spuId;*/

    @ApiModelProperty(value = "PC端产品描述")
    @NotNull(message = "PC端产品描述不能为空!")
    private String pcDesc;

    @ApiModelProperty(value = "客户端产品描述")
    @NotNull(message = "客户端产品描述不能为空!")
    private String mobileDesc;

    @ApiModelProperty(value = "预定须知")
    private String bookingNotes;
}
