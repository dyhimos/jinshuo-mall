package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.domain.item.ad.AdPositionId;
import com.jinshuo.mall.domain.item.ad.Advertisement;
import com.jinshuo.mall.service.item.application.assermbler.AdAssembler;
import com.jinshuo.mall.service.item.application.dto.AdvertisementDto;
import com.jinshuo.mall.service.item.mybatis.AdvertisementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/19.
 */
@Service
public class AdvertisementQueryService {

    @Autowired
    private AdvertisementRepo advertisementRepo;


    public Advertisement getById(Long advertisementId) {
        return advertisementRepo.queryById(advertisementId);
    }

    public Optional<Advertisement> getOptionById(Long advertisementId) {
        return Optional.ofNullable(getById(advertisementId));
    }

    public List<AdvertisementDto> getListByPositionId(Long adPositionId) {
        List<AdvertisementDto> dtos = advertisementRepo.findByPositionId(adPositionId)
                .stream()
                .map(advertisement -> AdAssembler.assembleAdvertisementDto(advertisement))
                .collect(Collectors.toList());
        return dtos;
    }

    public List<AdvertisementDto> queryByUsedPositionId(Long adPositionId,String areaName) {
        Advertisement temp = Advertisement.builder().areaNames(areaName).adPositionId(new AdPositionId(adPositionId)).build();
        List<AdvertisementDto> dtos = advertisementRepo.queryByUsedPositionId(temp)
                .stream()
                .map(advertisement -> AdAssembler.assembleAdvertisementDto(advertisement))
                .collect(Collectors.toList());
        return dtos;
    }
}
