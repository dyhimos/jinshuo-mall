package com.jinshuo.mall.domain.item.spuOther;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dongyh on 2019/7/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpuOther extends IdentifiedEntity {

    /**
     * id
     */
    private SpuOtherId spuOtherId;

    /**
     * spuId
     */
    private SpuId spuId;

    /**
     * 是否展示销量 1、快递发货；2、到店消费
     */
    private Integer isShowSell;

    /**
     * 快递费
     */
    private BigDecimal courierFee;

    /**
     * 上架时间
     */
    private Date upTime;

    /**
     * 下架时间
     */
    private Date downTime;

    /**
     * 开始抢购时间
     */
    private Date buyStartDate;

    /**
     * 抢购结束时间
     */
    private Date buyEndDate;

    /**
     * 活动地址
     */
    private String activityAddress;

    /**
     * 活动时间
     */
    private String activityDate;

    /**
     * 卷使用开始时间
     */
    private Date activityStartDate;

    /**
     * 卷使用结束时间
     */
    private Date activityEndDate;

    /**
     * 是否抢购商品
     */
    private Integer isScareBuy;

    public void checkGood() {
        Date now = new Date();
        if (now.after(this.downTime)) {
            throw new IcBizException(IcReturnCode.IC201020.getCode(), IcReturnCode.IC201020.getMsg());
        }
        if (0 == isScareBuy) {
            if (null == this.buyEndDate || null == this.buyStartDate) {
                throw new IcBizException(IcReturnCode.IC201024.getCode(), IcReturnCode.IC201024.getMsg());
            }
            if (now.after(this.buyEndDate)) {
                throw new IcBizException(IcReturnCode.IC201021.getCode(), IcReturnCode.IC201021.getMsg());
            }
            if (now.before(this.buyStartDate)) {
                throw new IcBizException(IcReturnCode.IC201022.getCode(), IcReturnCode.IC201022.getMsg());
            }
        }
    }

    public void checkData() {
        if ("0".equals(this.isScareBuy)) {
            if (null == this.buyStartDate || null == this.buyEndDate) {

            }
        }
    }

    public SpuOther update() {
        checkData();
        this.updateDate = new Date();
        return this;
    }

    public SpuOther update(SpuId spuId, Integer isShowSell, BigDecimal courierFee, Date upTime, Date downTime,
                           Date buyStartDate, Date buyEndDate, String activityAddress, String activityDate, Integer isScareBuy,Date activityStartDate,Date activityEndDate) {
        checkData();
        this.spuId = spuId;
        this.isShowSell = isShowSell;
        this.courierFee = courierFee;
        this.upTime = upTime;
        this.downTime = downTime;
        this.buyStartDate = buyStartDate;
        this.buyEndDate = buyEndDate;
        this.activityAddress = activityAddress;
        this.activityDate = activityDate;
        this.isScareBuy = isScareBuy;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        return this;
    }
}
