package com.jinshuo.mall.service.user.application.qry;

import com.jinshuo.mall.service.item.application.cmd.TargetCmd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/8/14.
 */
@Data
public class OrderCouponQry {

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    private List<TargetCmd> targetCmds;
}
