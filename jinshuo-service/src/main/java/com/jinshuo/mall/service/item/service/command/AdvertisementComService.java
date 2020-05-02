package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.mall.domain.item.ad.AdPositionId;
import com.jinshuo.mall.domain.item.ad.Advertisement;
import com.jinshuo.mall.service.item.application.cmd.AdvertisementCmd;
import com.jinshuo.mall.service.item.mybatis.AdvertisementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class AdvertisementComService {

    @Autowired
    private AdvertisementRepo advertisementRepo;


    /**
     * 新增广告
     *
     * @param cmd
     */
    public void create(AdvertisementCmd cmd) {
        String areaNames = "";
        if (null != cmd.getAreaNames() && cmd.getAreaNames().size() > 0) {
            areaNames = cmd.getAreaNames().stream().collect(Collectors.joining(","));
        } else {
            throw new IcBizException(IcReturnCode.IC201025.getCode(), IcReturnCode.IC201025.getMsg());
        }
        if (null != cmd.getId()) {
            update(cmd);
        } else {
            Advertisement advertisement = Advertisement.builder()
                    .adPositionId(new AdPositionId(cmd.getAdPositionId()))
                    .image(cmd.getImage())
                    .isEnabled(cmd.getIsEnabled())
                    .sort(cmd.getSort())
                    .titile(cmd.getTitile())
                    .url(cmd.getUrl())
                    .areaNames(areaNames)
                    .build();
            advertisement.insert();
            advertisementRepo.save(advertisement);
        }
    }

    /**
     * 修改广告
     *
     * @param cmd
     */
    public void update(AdvertisementCmd cmd) {
        String areaNames = "";
        if (null != cmd.getAreaNames()) {
            areaNames = cmd.getAreaNames().stream().collect(Collectors.joining(","));
        }
        Advertisement advertisement = Advertisement.builder()
                .adPositionId(new AdPositionId(cmd.getAdPositionId()))
                .image(cmd.getImage())
                .isEnabled(cmd.getIsEnabled())
                .sort(cmd.getSort())
                .titile(cmd.getTitile())
                .url(cmd.getUrl())
                .areaNames(areaNames)
                .build();
        advertisement.update(cmd.getId());
        advertisementRepo.update(advertisement);
    }

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    public int deleteAd(Long id) {
        return advertisementRepo.delete(id);
    }
}
