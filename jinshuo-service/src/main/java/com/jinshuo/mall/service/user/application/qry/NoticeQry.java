package com.jinshuo.mall.service.user.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 19458 on 2020/1/3.
 */
@Data
public class NoticeQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    /**
     * 0：查询首页通知 1：查询所有通知
     */
    @ApiModelProperty(value = "0：查询首页通知 1：查询所有通知")
    private Integer isShow;
}
