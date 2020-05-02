package com.jinshuo.mall.service.user.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2019/8/1.
 */
@Data
public class AddressQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    private Long memId;
}
