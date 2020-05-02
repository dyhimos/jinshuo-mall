package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 19458 on 2019/7/8.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQry {

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "查询类型")
    private String qryType; // 1：查询所有树

    @ApiModelProperty(value = "店铺id")
    private Long shopId;
}
