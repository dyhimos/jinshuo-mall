package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/8/29.
 */
@Data
public class UserDisShareProductDto {

    private String id;//编号
    private Integer isDefault;//是否默认 0是 1不是
    private String productId;//产品编号
    private String productName;//产品名称
    private BigDecimal productAmount;
    private String sketch;//简介
    private BigDecimal price;//价格
    private String pictureUrl;//封面图

    private Long rankId;//分销商等级
    private String rankName;//分销商等级
    private BigDecimal disPerP1;//一级分销比例
    private BigDecimal disMoneyP1;//一级分销佣金
    private BigDecimal disPerP2;//二级分销比例
    private BigDecimal disMoneyP2;//二级分销佣金
    private Date addTime;//添加时间

    private BigDecimal disMoney;//我的佣金

    public void init(){

    }
}
