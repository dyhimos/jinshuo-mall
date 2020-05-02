package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.utils.MathUtil;
import com.jinshuo.mall.domain.item.coupon.Coupon;
import com.jinshuo.mall.domain.item.couponlogs.CouponLogs;
import com.jinshuo.mall.domain.item.couponreceive.CouponReceive;
import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.service.item.application.cmd.CouponLogsCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuStockUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.CouponLogsRepo;
import com.jinshuo.mall.service.item.service.query.CouponQueryService;
import com.jinshuo.mall.service.item.service.query.CouponReceiveQueryService;
import com.jinshuo.mall.service.item.service.query.SkuQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class CouponLogsComService {

    @Autowired
    private CouponLogsRepo couponLogsRepo;

    @Autowired
    private CouponComService couponComService;

    @Autowired
    private CouponReceiveQueryService couponReceiveQueryService;

    @Autowired
    private CouponReceiveComService couponReceiveComService;

    @Autowired
    private SkuQueryService skuQueryService;

    @Autowired
    private CouponQueryService couponQueryService;

    @Autowired
    private SpuComService spuComService;

    /**
     * 使用优惠券（减库存）
     *
     * @param cmd
     * @Desc 使用优惠券并减库存 若优惠券为空则只减库存
     */
    @Transactional
    public void create(CouponLogsCreateCmd cmd) {
        try {
            cmd.getTargetCmds().forEach(targetCmd -> {
                SpuStockUpdateCmd spuStockUpdateCmd = SpuStockUpdateCmd.builder()
                        .skuId(targetCmd.getTargetId())
                        .stock(targetCmd.getCount())
                        .build();
                spuComService.cutStock(spuStockUpdateCmd);
            });
        } catch (Exception e) {
            log.info(" -- 减库存失败，继续使用优惠券！");
            log.error("系統错误,",e);
        }
        //若干领取id为空则只减免库存
        if (null == cmd.getCouponReceiveId()) {
            log.info(" -- 优惠券信息为空，只减库存！");
            return;
        }
        mathOrderOriginalAmount(cmd);
        //校验优惠券
        Coupon coupon = checkCoupon(cmd);
        //将领取记录置为已经使用
        couponReceiveComService.useCoupon(cmd.getCouponReceiveId());
        //将优惠券的已核销数+1
        couponComService.useCoupon(coupon);
        //新增使用记录
        CouponLogs couponLogs = new CouponLogs();
        BeanUtils.copyProperties(cmd, couponLogs);
        couponLogs.setMemId(cmd.getMemId());
        couponLogs.setCouponAmount(coupon.getCouponAmount());
        couponLogs.setOrderFinalAmount(MathUtil.subtract(cmd.getOrderOriginalAmount(), coupon.getCouponAmount(), 2));
        couponLogs.insert();
        couponLogsRepo.save(couponLogs);
    }

    /**
     * 校验库存及优惠券是否可用
     *
     * @param cmd
     * @return Coupon
     */
    @Transactional
    public Coupon checkCoupon(CouponLogsCreateCmd cmd) {
        cmd.getTargetCmds().forEach(targetCmd -> {
            SpuStockUpdateCmd spuStockUpdateCmd = SpuStockUpdateCmd.builder()
                    .skuId(targetCmd.getTargetId())
                    .stock(targetCmd.getCount())
                    .build();
            spuComService.checkStock(spuStockUpdateCmd);
        });

        //若干领取id为空则只减免库存
        if (null == cmd.getCouponReceiveId()) {
            log.info(" -- 校验库存及优惠券是否可用，只校验库存！");
            return null;
        }

        mathOrderOriginalAmount(cmd);
        //校验使用状态
        CouponReceive couponReceive = couponReceiveQueryService.getById(cmd.getCouponReceiveId());
        if (couponReceive == null) {
            throw new IcBizException(IcReturnCode.IC201005.getCode(), IcReturnCode.IC201005.getMsg());
        }
        // 校验是否过期，详细校验在coupon中
        if (-1 == couponReceive.getUseStatus()) {
            throw new IcBizException(IcReturnCode.IC201004.getCode(), IcReturnCode.IC201004.getMsg());
        } else if (1 == couponReceive.getUseStatus()) {
            throw new IcBizException(IcReturnCode.IC201001.getCode(), IcReturnCode.IC201001.getMsg());
        } else if (0 == couponReceive.getUseStatus()) {
            log.info(" -- " + cmd.getCouponReceiveId() + "优惠券正常！");
        } else {
            throw new IcBizException(IcReturnCode.IC201003.getCode(), IcReturnCode.IC201003.getMsg());
        }
        List<Long> list = cmd.getTargetCmds().stream().map(targetCmd -> {
            Sku sku = skuQueryService.getSkusBySkuId(targetCmd.getTargetId());
            if (null == sku) {
                throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
            }
            return sku.getSpuId().getId();
        }).collect(Collectors.toList());
        return couponComService.checkCoupon(cmd.getMemId(), couponReceive.getCouponId(), list, cmd.getOrderOriginalAmount());
    }


    /**
     * 设置订单金额
     *
     * @param cmd
     * @return Coupon
     */
    public CouponLogsCreateCmd mathOrderOriginalAmount(CouponLogsCreateCmd cmd) {
        cmd.setOrderOriginalAmount(skuQueryService.getAmountBySkuId(cmd.getTargetCmds()));
        return cmd;
    }


}
