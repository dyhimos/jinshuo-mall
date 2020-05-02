package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.mall.domain.item.couponitem.CouponItem;
import com.jinshuo.mall.service.item.application.assermbler.CouponItemAssembler;
import com.jinshuo.mall.service.item.application.dto.CouponItemDto;
import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.item.application.qry.TagQry;
import com.jinshuo.mall.service.item.mybatis.CouponItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class CouponItemQueryService {

    @Autowired
    private CouponItemRepo couponItemRepo;

    @Autowired
    private SpuQueryService spuQueryService;

    public CouponItem getById(Long id) {
        return couponItemRepo.queryById(id);
    }

    public Optional<CouponItem> getOptionById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    public PageInfo getByPage(TagQry qry) {
        PageInfo pageInfo = couponItemRepo.getByPage(qry);
        List<CouponItem> tags = pageInfo.getList();
        return pageInfo;
    }

    public List<CouponItem> findListByCouponId(Long couponId) {
        return couponItemRepo.queryByCouponId(couponId);
    }

    public List<CouponItemDto> findDtosByCouponId(Long couponId) {
        List<CouponItemDto> dtos = findListByCouponId(couponId).stream()
                .map(couponItem -> {
                    CouponItemDto dto = CouponItemAssembler.assembleCouponDto(couponItem);
                    if (1 == couponItem.getType()) {
                        SpuDto spu = spuQueryService.findBySpuId(couponItem.getTargetId());
                        if (null != spu) {
                            dto.setProduct(spu.getPictureUrl(), spu.getName(), spu.getPrice(), spu.getStock());
                        }
                    }
                    return dto;
                }).collect(Collectors.toList());
        return dtos;
    }

    public List<CouponItem> findByTargetId(Long targetId) {
        if (null == targetId) {
            return null;
        }
        return couponItemRepo.queryByTargetId(targetId);
    }


    /**
     * 校验可用产品(指定商品可用)
     *
     * @param couponId
     * @param targetIds
     * @return
     */
    public void checkCouponIn(Long couponId, List<Long> targetIds) {
        CouponItem couponItem;
        for (Long id : targetIds) {
            couponItem = couponItemRepo.checkCoupon(couponId, id);
            if (null == couponItem) {
                throw new IcBizException(IcReturnCode.IC201010.getCode(), IcReturnCode.IC201010.getMsg());
            }
        }
    }

    /**
     * 校验可用产品(指定商品不可用)
     *
     * @param couponId
     * @param targetIds
     * @return
     */
    public void checkCouponOut(Long couponId, List<Long> targetIds) {
        CouponItem couponItem;
        for (Long id : targetIds) {
            couponItem = couponItemRepo.checkCoupon(couponId, id);
            if (null != couponItem) {
                throw new IcBizException(IcReturnCode.IC201010.getCode(), IcReturnCode.IC201010.getMsg());
            }
        }
    }

    public List<CouponItem> getByExmple(CouponItem couponItem){
        return couponItemRepo.findByExmple(couponItem);
    }
}
