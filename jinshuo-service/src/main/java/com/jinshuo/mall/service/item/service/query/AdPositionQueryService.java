package com.jinshuo.mall.service.item.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.constant.DefaultShopId;
import com.jinshuo.mall.domain.item.ad.AdPosition;
import com.jinshuo.mall.service.item.application.assermbler.AdAssembler;
import com.jinshuo.mall.service.item.application.dto.AdPositionDto;
import com.jinshuo.mall.service.item.application.qry.AdQry;
import com.jinshuo.mall.service.item.mybatis.AdPositionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Slf4j
@Service
public class AdPositionQueryService {

    @Autowired
    private AdPositionRepo adPositionRepo;

    @Autowired
    private AdvertisementQueryService advertisementQueryService;


    public AdPosition getById(Long adPositionId) {
        return adPositionRepo.queryById(adPositionId);
    }

    public Optional<AdPosition> getOptionById(Long adPositionId) {
        return Optional.ofNullable(getById(adPositionId));
    }


    public PageInfo getPageInfo(AdQry qry) {
        if (null == qry.getShopId()) {
            qry.setShopId(DefaultShopId.SHOPID);
        }
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        AdPosition temp = new AdPosition();
        temp.setShopId(qry.getShopId());
        List<AdPosition> list = adPositionRepo.findAll(temp);
        PageInfo pageInfo = new PageInfo(list);
        List<AdPositionDto> dtos = list.stream().map(adPosition ->
                AdAssembler.assembleDto(adPosition))
                .collect(Collectors.toList());
        for (AdPositionDto adPositionDto : dtos) {
            adPositionDto.setList(advertisementQueryService.getListByPositionId(Long.parseLong(adPositionDto.getId())));
        }
        pageInfo.setList(dtos);
        return pageInfo;
    }


    /**
     * 根据广告位id查询广告
     *
     * @param qry
     * @return
     */
    public AdPositionDto queryAdByPositionId(AdQry qry) {
        AdPosition adPosition = adPositionRepo.queryById(qry.getId());
        AdPositionDto adPositionDto = AdAssembler.assembleDto(adPosition);
        adPositionDto.setList(advertisementQueryService.getListByPositionId(Long.parseLong(adPositionDto.getId())));
        return adPositionDto;
    }

    /**
     * 根据广告位code查询广告
     *
     * @param qry
     * @return
     */
    public AdPositionDto queryAdByCode(AdQry qry) {
        log.info(" -- 根据广告位code查询广告,输入参数：", qry);
        if (null == qry.getShopId()) {
            qry.setShopId(DefaultShopId.SHOPID);
        }
        if (StringUtils.isBlank(qry.getCode())) {
            return null;
        }
        AdPosition adPosition = adPositionRepo.queryByCode(qry.getCode(), qry.getShopId());
        if (null == adPosition) {
            return null;
        }
        AdPositionDto adPositionDto = AdAssembler.assembleDto(adPosition);
        adPositionDto.setList(advertisementQueryService.queryByUsedPositionId(Long.parseLong(adPositionDto.getId()), qry.getAreaName()));
        return adPositionDto;
    }

    /**
     * 弹出广告查询
     *
     * @param qry
     * @return
     */
    public AdPositionDto queryPopAdByCode(AdQry qry) {
        AdPositionDto dto = queryAdByCode(qry);
        if (null != dto && null != dto.getShowType() && 0 == dto.getShowType()) {
            if (null != dto.getStartTime() && null != dto.getEndTime()) {
                Date now = new Date();
                if (now.after(dto.getStartTime()) && now.before(dto.getEndTime())) {
                    dto.setPopUpFlag(true);
                }
            }
        }
        return dto;
    }


}
