package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2020/1/2.
 */
@Data
public class CategoryParameterCmd {
    private Long categoryId;
    private List<Long> parameterIds;
}
