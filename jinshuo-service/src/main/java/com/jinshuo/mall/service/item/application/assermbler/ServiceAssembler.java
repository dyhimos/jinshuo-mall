package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.service.ServiceSupport;
import com.jinshuo.mall.service.item.application.dto.ServiceSupportDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/7/22.
 */
public class ServiceAssembler {

    /**
     * @param serviceSupport
     * @return
     */
    public static ServiceSupportDto assembleDto(ServiceSupport serviceSupport) {
        if (null == serviceSupport) {
            return null;
        }
        ServiceSupportDto dto = new ServiceSupportDto();
        BeanUtils.copyProperties(serviceSupport, dto);
        dto.setId(serviceSupport.getServiceSupportId().getId().toString());
        if (null != serviceSupport.getShopId()) {
            dto.setShopId(serviceSupport.getShopId().toString());
        }
        return dto;
    }
}
