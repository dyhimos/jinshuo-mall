package com.jinshuo.mall.service.order.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询寄样信息列表
 * @Classname GoodsSimpleQry
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dongyh
 */
@Data
public class GoodsSimpleQry {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "店铺id")
    private Long memberId;

    @ApiModelProperty(value = "收货人名称")
    private String userName;

    @ApiModelProperty(value = "收件人手机号码")
    private String userPhone;

    @ApiModelProperty(value = "寄样单号")
    private String sampleNo;

    @ApiModelProperty(value = "寄样状态")
    private Integer sampleStatus;
}
