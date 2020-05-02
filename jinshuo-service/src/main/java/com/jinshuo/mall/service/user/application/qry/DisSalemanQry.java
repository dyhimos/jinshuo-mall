package com.jinshuo.mall.service.user.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/9/2.
 */
@Data
public class DisSalemanQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "空：全部客户  1我的客户  2我的团队 ")
    private Integer qryType;

    @ApiModelProperty(value = "空：全部  1今日 2昨日 3近七日")
    private Integer dateType;

    private Long userId;
}
