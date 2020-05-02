package com.jinshuo.mall.service.finance.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 资金查询
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dongyh
 */
@Data
public class FinanceAccountCashQry {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "会员编号")
    private String memNo;

    @ApiModelProperty(value = "会员名字")
    private String nickname;
}
