package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.ad.AdPosition;
import com.jinshuo.mall.domain.item.ad.Advertisement;
import com.jinshuo.mall.service.item.application.dto.AdPositionDto;
import com.jinshuo.mall.service.item.application.dto.AdvertisementDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dyh on 2019/7/22.
 */
public class AdAssembler {

    /**
     * @param adPosition
     * @return
     */
    public static AdPositionDto assembleDto(AdPosition adPosition) {
        if (null == adPosition) {
            return null;
        }
        AdPositionDto dto = new AdPositionDto();
        BeanUtils.copyProperties(adPosition, dto);
        dto.setId(adPosition.getAdPositionId().getId().toString());
        if (null != adPosition.getShopId()) {
            dto.setShopId(adPosition.getShopId().toString());
        }
        return dto;
    }

    /**
     * @param advertisement
     * @return
     */
    public static AdvertisementDto assembleAdvertisementDto(Advertisement advertisement) {
        if (null == advertisement) {
            return null;
        }
        AdvertisementDto dto = new AdvertisementDto();
        BeanUtils.copyProperties(advertisement, dto);
        dto.setId(advertisement.getAdvertisementId().getId().toString());
        dto.setAdPositionId(advertisement.getAdPositionId().getId().toString());
        if (StringUtils.isNotBlank(advertisement.getAreaNames())) {
            dto.setAreaNames(Arrays.asList(advertisement.getAreaNames().split(",")));
        }else{
            List<String> strs = new ArrayList<>();
            dto.setAreaNames(strs);
        }
        return dto;
    }
}
