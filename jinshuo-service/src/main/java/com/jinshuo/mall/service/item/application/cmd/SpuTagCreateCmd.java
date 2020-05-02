package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 19458 on 2019/7/24.
 */
@Data
public class SpuTagCreateCmd implements Serializable {
    private List<Long> tagIds;
}
