package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/7/31.
 */
@Data
@Builder
@Getter
@Accessors(chain = true)
@ToString(callSuper = true)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class SpuStockUpdateCmd {

    @ApiModelProperty(value = "skuId")
    @NotNull(message = "skuId不能为空!")
    private Long skuId;

    @ApiModelProperty(value = "减扣库存数")
    @NotNull(message = "减扣库存数不能为空!")
    private Integer stock;
}
