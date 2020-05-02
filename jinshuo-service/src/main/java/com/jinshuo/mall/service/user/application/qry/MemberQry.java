package com.jinshuo.mall.service.user.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 会员查询条件
 * @Author: dongyh
 * @CreateDate: 2019/8/29 12:08
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/8/29 12:08
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class MemberQry {
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    private Integer isDis;

    private Integer isFans;

    private String name;

    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "父昵称")
    private String parentName;

    private Long memId;

    private String path;
}
