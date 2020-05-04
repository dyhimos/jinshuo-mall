package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.mall.domain.item.ad.AdPosition;
import com.jinshuo.mall.service.item.application.cmd.AdPositionCmd;
import com.jinshuo.mall.service.item.mybatis.AdPositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class AdPositionComService {

    @Autowired
    private AdPositionRepo adPositionRepo;


    /**
     * 新增广告位
     *
     * @param cmd
     */
    public void create(AdPositionCmd cmd) {
        if (null == cmd.getShopId()) {
            cmd.setShopId(DefaultShopId.SHOPID);
        }
        AdPosition temp = adPositionRepo.queryByCode(cmd.getCode(), cmd.getShopId());
        if (null == cmd.getId() && null != temp) {
            throw new IcBizException(IcReturnCode.IC201016.getCode(), IcReturnCode.IC201016.getMsg());
        }
        if (null != temp && null != cmd.getId() && temp.getAdPositionId().getId().longValue() != cmd.getId().longValue()) {
            throw new IcBizException(IcReturnCode.IC201016.getCode(), IcReturnCode.IC201016.getMsg());
        }
        if (null != cmd.getId()) {
            update(cmd);
        } else {
            AdPosition adPosition = AdPosition.builder()
                    .code(cmd.getCode())
                    .name(cmd.getName())
                    .desc(cmd.getDesc())
                    .isCycle(cmd.getIsCycle())
                    .adStatus(cmd.getAdStatus())
                    .startTime(cmd.getStartTime())
                    .endTime(cmd.getEndTime())
                    .areaIds(cmd.getAreaIds())
                    .showType(cmd.getShowType())
                    .gapTime(cmd.getGapTime())
                    .areaIds(cmd.getAreaIds())
                    .memberType(cmd.getMemberType())
                    .shopId(cmd.getShopId())
                    .build();
            adPosition.insert();
            adPositionRepo.save(adPosition);
        }
    }

    /**
     * 修改广告位
     *
     * @param cmd
     */
    public void update(AdPositionCmd cmd) {
        AdPosition adPosition = AdPosition.builder()
                .code(cmd.getCode())
                .name(cmd.getName())
                .desc(cmd.getDesc())
                .isCycle(cmd.getIsCycle())
                .adStatus(cmd.getAdStatus())
                .startTime(cmd.getStartTime())
                .endTime(cmd.getEndTime())
                .areaIds(cmd.getAreaIds())
                .showType(cmd.getShowType())
                .gapTime(cmd.getGapTime())
                .areaIds(cmd.getAreaIds())
                .memberType(cmd.getMemberType())
                .shopId(cmd.getShopId())
                .build();
        adPosition.update(cmd.getId());
        adPositionRepo.update(adPosition);
    }

    /**
     * 删除广告位
     *
     * @param id
     * @return
     */
    public int deleteAdPosition(Long id) {
        return adPositionRepo.delete(id);
    }

    /**
     * 修改广告位状态
     *
     * @param id
     * @return
     */
    public int updateStatus(Long id, Integer adStatus) {
        return adPositionRepo.updateStatus(id, adStatus);
    }
}
