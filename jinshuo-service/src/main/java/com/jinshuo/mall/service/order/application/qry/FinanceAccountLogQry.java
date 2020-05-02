package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SpuQry
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dongyh
 */
@Data
public class FinanceAccountLogQry {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "类型 0:收入,1:支出")
    private Integer type;

    @ApiModelProperty(value = "记录来源 0:后台充值 1余额支出 2：支付宝支付 3：微信支付")
    private  Integer source;
}
