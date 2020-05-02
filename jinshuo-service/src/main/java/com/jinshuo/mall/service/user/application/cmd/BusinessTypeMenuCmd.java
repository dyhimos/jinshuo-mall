package com.jinshuo.mall.service.user.application.cmd;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/10/12.
 */
@Data
public class BusinessTypeMenuCmd {
    private Integer businessTypeId;
    private List<Long> menuIds;
}
