package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 店铺id
* @Description:
* @Author:         dongyh
* @CreateDate:     2019/9/18 17:04
* @UpdateUser:     dongyh
* @UpdateDate:     2019/9/18 17:04
* @UpdateRemark:
* @Version:        1.0
*/
@Data
public class ShopIdCmd {

    @ApiModelProperty(value = "店铺id ")
    private Long shopId;
}
