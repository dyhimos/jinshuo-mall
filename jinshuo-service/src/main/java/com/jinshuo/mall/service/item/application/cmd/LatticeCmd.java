package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 19458 on 2019/9/24.
 */
@Data
public class LatticeCmd {
    private Long id;

    @NotNull(message = "父分类不能为空")
    private String name;

    @NotNull(message = "排序序列")
    private Integer orderSeq;

    @NotNull(message = "类目图片")
    private String pictureUrl;

    private Long shopId;

    @NotNull(message = "是否展示 0:是 1:是展示")
    private Integer isShow;
}
