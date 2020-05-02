package com.jinshuo.mall.domain.item.couponreceive;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UserIdUtils;
import lombok.*;

import java.util.Date;

/**
 * Created by dongyh on 2019/7/31.
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponReceive extends IdentifiedEntity {
    private CouponReceiveId couponReceiveId;
    private Long couponId;
    private Long memId;
    private String couponCode;
    private Date receiveTime;
    private Date useTime;
    private Integer useStatus; // 状态，1为已使用，0为已领取未使用，-1为已过期

    public CouponReceive insert() {
        this.couponReceiveId = new CouponReceiveId(CommonSelfIdGenerator.generateId());
        this.couponCode = CommonSelfIdGenerator.generateId().toString();
        this.useStatus = 0;
        this.receiveTime = new Date();
        this.memId = UserIdUtils.getUserId();
        super.preInsert();
        return this;
    }

    /**
     * 自动发放优惠券
     * @param memId
     * @return
     */
    public CouponReceive insert(Long memId) {
        this.couponReceiveId = new CouponReceiveId(CommonSelfIdGenerator.generateId());
        this.couponCode = CommonSelfIdGenerator.generateId().toString();
        this.useStatus = 0;
        this.receiveTime = new Date();
        this.memId = memId;
        super.preInsert();
        return this;
    }

    public CouponReceive checkUseStatus() {
        if (1 == this.useStatus) {
            throw new IcBizException(IcReturnCode.IC201001.getCode(), IcReturnCode.IC201001.getMsg());
        }
        if (-1 == this.useStatus) {
            throw new IcBizException(IcReturnCode.IC201002.getCode(), IcReturnCode.IC201002.getMsg());
        }
        if (0 == this.useStatus) {
            return this;
        }
        throw new IcBizException(IcReturnCode.IC201003.getCode(), IcReturnCode.IC201003.getMsg());
    }
}
