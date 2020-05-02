package com.jinshuo.mall.service.user.application.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/9/4.
 */
@Data
public class MyUpgradeGapDto {
    /**
     * 升级数量开关 0开 1关
     */
    private Integer upgradeNumberSwitch;
    /**
     * 升级数量
     */
    private Integer upgradeNumber;
    /**
     * 升级销售额开关 0开 1关
     */
    private Integer upgradeSalesSwitch;
    /**
     * 升级销售额
     */
    private BigDecimal upgradeSales;
    /**
     * 差的数量
     */
    private Integer gapCount;
    /**
     * 差的金额
     */
    private BigDecimal gapAmount;
    /**
     * 分销商开关
     */
    private Integer subordinateSwitch;
    /**
     * 分销商数量
     */
    private Integer gapSubordinateCount;
    /**
     * 差的分销商数量
     */
    private Integer subordinateCount;

    private Integer isComSubord = 1;// 0-》达标 1-》不达标
    private Integer isComAmount = 1;// 0-》达标 1-》不达标
    private Integer isComCount = 1;// 0-》达标 1-》不达标

    public MyUpgradeGapDto changeGapCount(Integer gapCount) {
        if (gapCount < 0) {
            this.gapCount = 0;
        } else {
            this.gapCount = gapCount;
        }
        return this;
    }

    public MyUpgradeGapDto changegapAmount(BigDecimal gapAmount) {
        if (-1 == gapAmount.compareTo(new BigDecimal(0))) {
            this.gapAmount = new BigDecimal(0);
        } else {
            this.gapAmount = gapAmount;
        }
        return this;
    }
}
