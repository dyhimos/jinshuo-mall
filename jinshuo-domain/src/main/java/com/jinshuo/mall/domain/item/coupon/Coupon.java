package com.jinshuo.mall.domain.item.coupon;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.DayUtils;
import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by dongyh on 2019/7/29.
 */
@Slf4j
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends IdentifiedEntity {
    private CouponId couponId;                  //
    private String name;             //优惠券名称
    private Integer type;             //优惠券类型 1现金券 2满减券 3折扣券
    private BigDecimal couponAmount;//面值
    private BigDecimal amount;           //减免金额
    private Integer maxLimit;        //发行数量
    private Integer receivedQuantity;//已领取数量
    private Integer checkquantity;    //已核销数量
    private Integer isCondition;     //门槛规则 1、满减；2、无门槛
    private Integer scopeType;       //适用范围类型 1、全部商品可用；2、指定商品可用；3、指定商品不可用
    private Date receiveStartTime;   //可领取开始时间-结束时间
    private Date receiveEndTime;    //可领取结束时间
    private Integer validitType;     //有效期类型  1、表示指定时间 比如 12天 ；2、表示固定时间
    private Date validityStartDate; //绝对有效期开始时间
    private Date vaildityEndDate;   //绝对有效期-结束时间
    private Integer vaildityDays;    //相对有效期天数
    private Integer gainCount;// 限领张数 每个用户可领取次数 0 不限 >0 次数
    private Integer gainMethod;//用户获取方式 1需要用户主动领取 2直接发放到用户账户
    private String couponDesc;//优惠说明
    private Integer sort;//排序
    private Integer couponStatus;           //是否生效 1、未开始；2、进行中；3、已结束
    private Integer auditStatus;      //审核状态
    private Integer isShow;    //是否展示
    private Long shopId;//店铺ID
    private Integer forPeople;//适用人群 0:所有人 1:成为会员但不是粉丝

    public void insert() {
        this.receivedQuantity = 0;
        this.checkquantity = 0;
        checkValiditType();
        super.preInsert();
        this.couponId = new CouponId(CommonSelfIdGenerator.generateId());
    }

    public Coupon checkValiditType() {
        if (2 == this.validitType) {
            if (null == this.validityStartDate || null == this.vaildityEndDate) {
                throw new IcBizException(IcReturnCode.IC201013.getCode(), IcReturnCode.IC201013.getMsg());
            }
        } else {
            if (null == this.vaildityDays) {
                throw new IcBizException(IcReturnCode.IC201013.getCode(), IcReturnCode.IC201013.getMsg());
            }
        }
        return this;
    }

    public void update() {
        checkValiditType();
        this.updateDate = new Date();
    }


    /**
     * 领取优惠券减库存
     *
     * @return this
     */
    public Coupon cutStock() {
        Date date = new Date();
        //判断活动
        if (2 != this.couponStatus) {
            log.info("--- 不在活动时间！");
            //throw new IcBizException();
        }
        if (null == this.receivedQuantity) {
            this.receivedQuantity = 0;
        }
        //判断是否超发
        if (this.receivedQuantity >= this.maxLimit) {
            log.info("--- 严重超发");
            throw new IcBizException(IcReturnCode.IC201006.getCode(), IcReturnCode.IC201006.getMsg());
        }
        this.receivedQuantity += 1;
        if (this.maxLimit == this.receivedQuantity) {
            this.couponStatus = 3;
        }
        return this;
    }

    /**
     * 使用优惠券(使用前校验及增加核销数)
     *
     * @param targetIds
     * @return this
     */
    public Coupon checkCoupon(Long memId, List<Long> targetIds, BigDecimal orderOriginalAmount) {
        checkCouponDate(); //校验优惠券使用有效期
        checkRange(targetIds); //校验产品范围
        checkIsCondition(orderOriginalAmount);//判断优惠券是否达到满减金额
        checkGainCount(memId); // 校验产品使用次数
        return this;
    }

    /**
     * 使用优惠券(使用前校验及增加核销数)
     *
     * @param
     * @return this
     */
    public Coupon useCoupon() {
        //使用前已经校验范围
        /*checkGainCount(); // 校验产品使用次数
        checkCouponDate(); //校验优惠券使用有效期
        checkRange(targetIds); //校验产品范围
        checkIsCondition(orderOriginalAmount);//判断优惠券是否达到满减金额*/
        if (null == this.checkquantity) {
            this.checkquantity = 0;
        }
        this.checkquantity += 1;
        return this;
    }

    /**
     * 校验优惠券使用有效期
     *
     * @return this
     */
    public Coupon checkCouponDate() {
        Date date = new Date();
        log.info(this.couponId.getId() + "1、表示指定时间 比如 12天 ；2、表示固定时间(validitType):" + this.validitType + ";now : " + date);
        //判断是否有效期  1、表示指定时间 比如 12天 ；2、表示固定时间
        if (2 == this.validitType) {
            log.info(this.couponId.getId() + "校验优惠券使用有效期-开始日期(validityStartDate):" + this.validityStartDate);
            log.info(this.couponId.getId() + "校验优惠券使用有效期-结束日期(vaildityEndDate):" + this.vaildityEndDate);
            if (date.before(this.validityStartDate) || date.after(this.vaildityEndDate)) {
                throw new IcBizException(IcReturnCode.IC201004.getCode(), IcReturnCode.IC201004.getMsg());
            }
        } else {
            log.info(this.couponId.getId() + "校验优惠券使用有效期-有效天数(vaildityDays):" + this.vaildityDays);
            if (null != this.vaildityDays) {
                if (DayUtils.mathDay(this.createDate, new Date()) > this.vaildityDays) {
                    throw new IcBizException(IcReturnCode.IC201004.getCode(), IcReturnCode.IC201004.getMsg());
                }
            } else {
                throw new IcBizException(IcReturnCode.IC201004.getCode(), IcReturnCode.IC201004.getMsg());
            }
        }
        return this;
    }

    /**
     * 校验产品范围
     *
     * @param targetIds
     * @return this
     */
    public Coupon checkRange(List<Long> targetIds) {
        log.info(this.couponId.getId() + "校验产品范围(targetId):" + targetIds);
        log.info(this.couponId.getId() + "校验产品范围(scopeType1、全部商品可用；2、指定商品可用；3、指定商品不可用):" + this.scopeType);
        //return this;
        switch (this.scopeType) {
            //1、全部商品可用
            case 1:
                log.info(" -- 全部商品可用");
                return this;
            //2、指定商品可用
            case 2:
                //SpringUtil.getBean(CouponItemQueryService.class).checkCouponIn(this.getCouponId().getId(), targetIds);
                CouponItem couponItem;
                for (Long id : targetIds) {
                    /*couponItem = SpringUtil.getBean(CouponItemRepo.class).checkCoupon(this.getCouponId().getId(), id);
                    if (null == couponItem) {
                        throw new IcBizException(IcReturnCode.IC201010.getCode(), IcReturnCode.IC201010.getMsg());
                    }*/
                }

                return this;
            //3、指定商品不可用
            case 3:
                //SpringUtil.getBean(CouponItemQueryService.class).checkCouponOut(this.getCouponId().getId(), targetIds);
                CouponItem couponItemCanUse;
                for (Long id : targetIds) {
                    //couponItemCanUse = SpringUtil.getBean(CouponItemRepo.class).checkCoupon(this.getCouponId().getId(), id);
                    /*if (null != couponItemCanUse) {
                        throw new IcBizException(IcReturnCode.IC201010.getCode(), IcReturnCode.IC201010.getMsg());
                    }*/
                }
            default:
                throw new IcBizException(IcReturnCode.IC201003.getCode(), IcReturnCode.IC201003.getMsg());
        }
    }

    /**
     * 校验产品使用次数
     *
     * @return this
     */
    public Coupon checkGainCount(Long memId) {
        log.info(this.couponId.getId() + "校验产品使用次数(scopeType):" + this.gainCount + "; couponId : " + this.getCouponId().getId());
        if (0 == this.gainCount) {
            return this;
        }
      /*  List<CouponLogs> couponLogs = SpringUtil.getBean(CouponLogsRepo.class).queryMyLogsByCouponId(memId, this.getCouponId().getId());
        if (null != couponLogs) {
            log.info(this.couponId.getId() + "校验产品使用次数(couponLogs):" + couponLogs.size());
            if (couponLogs.size() > this.gainCount) {
                throw new IcBizException(IcReturnCode.IC201011.getCode(), IcReturnCode.IC201011.getMsg());
            }
        } else {
            log.info(this.couponId.getId() + "校验产品使用次数(couponLogs): NULL");
        }*/
        return this;
    }

    /**
     * 判断优惠券是否在有效期
     *
     * @return Boolean false：不在有效期
     */
    public Boolean checkExpired() {
        log.info(this.couponId.getId() + "判断优惠券是否在有效期:");
        if (2 == this.validitType) {
            if (new Date().after(this.vaildityEndDate)) {
                return false;
            }
        } else {
            if (DayUtils.mathDay(this.createDate, new Date()) > this.vaildityDays) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断优惠券是否达到满减金额
     *
     * @param orderOriginalAmount
     * @return Coupon
     */
    public Coupon checkIsCondition(BigDecimal orderOriginalAmount) {
        //门槛规则 1、满减；2、无门槛
        log.info(this.couponId.getId() + ";isCondition isCondition: " + this.isCondition + "判断优惠券是否达到满减金额 orderOriginalAmount:" + orderOriginalAmount + "; this.amount :" + this.amount);
        if (1 == this.isCondition && -1 == orderOriginalAmount.compareTo(this.amount)) {
            throw new IcBizException(IcReturnCode.IC201012.getCode(), IcReturnCode.IC201012.getMsg());
        }
        return this;
    }
}
