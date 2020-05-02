package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/11/18.
 */
@Data
public class ParameterCmd {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "0-> 商品列表页筛选  1-> 分类页筛选  2两者都有")
    private List<Integer> type;

    @ApiModelProperty(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "是否单选 0是单选 1否（多选")
    private Integer singleFlag;

    private List<ParameterValueCmd> params;
}
