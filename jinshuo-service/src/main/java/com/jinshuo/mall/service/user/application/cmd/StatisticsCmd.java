package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2020/4/2.
 */
@Data
public class StatisticsCmd {
    /**
     * 访问类型 1 页面
     */
    @ApiModelProperty(value = "访问类型 1 页面")
    private Integer statisticsType;

    /**
     * 目标代码 如页面代码
     */
    @ApiModelProperty(value = "目标代码 如页面代码 100001(首页)  100002->分类页  100003->列表页 100004->详情页" +
            " 100005->会员中心 100006->购物车页面 100007->支付确认页面")
    private String statisticsCode;
}
