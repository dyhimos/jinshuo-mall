package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import com.jinshuo.mall.domain.item.spuOther.SpuOther;
import com.jinshuo.mall.domain.item.spuOther.SpuOtherId;
import com.jinshuo.mall.service.item.application.cmd.SpuOtherCreateCmd;
import com.jinshuo.mall.service.item.application.cmd.SpuOtherUpdateCmd;
import com.jinshuo.mall.service.item.mybatis.SpuOtherRepo;
import com.jinshuo.mall.service.item.service.query.SpuOtherQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class SpuOtherComService {

    @Autowired
    private SpuOtherRepo spuOtherRepo;

    @Autowired
    private SpuOtherQueryService spuOtherQueryService;


    /**
     * 新增商品其他设置
     *
     * @param cmd
     */
    public void create(SpuOtherCreateCmd cmd) {
        SpuOther spuOther1 = spuOtherQueryService.findBySpuId(cmd.getSpuId());
        SpuOtherId spuOtherId = new SpuOtherId(CommonSelfIdGenerator.generateId());
        if (null != spuOther1) {
            spuOtherId = spuOther1.getSpuOtherId();
        }
        SpuOther spuOther = SpuOther.builder()
                .spuOtherId(spuOtherId)
                .courierFee(cmd.getCourierFee())
                .downTime(cmd.getDownTime())
                .upTime(cmd.getUpTime())
                .spuId(new SpuId(cmd.getSpuId()))
                .activityAddress(cmd.getActivityAddress())
                .activityDate(cmd.getActivityDate())
                .buyEndDate(cmd.getBuyEndDate())
                .buyStartDate(cmd.getBuyStartDate())
                .isScareBuy(cmd.getIsScareBuy())
                .isShowSell(cmd.getIsShowSell())
                .activityStartDate(cmd.getActivityStartDate())
                .activityEndDate(cmd.getActivityEndDate())
                .build();
        if (null == spuOther1) {
            spuOther.preInsert();
            spuOtherRepo.save(spuOther);
        } else {
            spuOther.update();
            spuOtherRepo.update(spuOther);
        }
    }

    /**
     * 修改商品其他设置
     *
     * @param cmd
     */
    public void update(SpuOtherUpdateCmd cmd) {
        spuOtherQueryService.findOptionalBySpuId(cmd.getSpuId())
                .map(spuOther -> spuOther.update(new SpuId(cmd.getSpuId()), cmd.getIsShowSell(), cmd.getCourierFee(), cmd.getUpTime(), cmd.getDownTime(),
                        cmd.getBuyStartDate(), cmd.getBuyEndDate(), cmd.getActivityAddress(), cmd.getActivityDate(), cmd.getIsScareBuy(), cmd.getActivityStartDate(), cmd.getActivityEndDate()))
                .ifPresent(spuOther -> spuOtherRepo.update(spuOther));
    }

    public void checkSpuOther(Long spuId) {
        log.info("查询spu_other的spuId为{}", spuId);
        SpuOther spuOther = spuOtherQueryService.findBySpuId(spuId);
        if (null == spuOther) {
            throw new IcBizException(IcReturnCode.IC201007.getCode(), IcReturnCode.IC201007.getMsg());
        }
        spuOther.checkGood();
    }
}
