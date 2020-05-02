package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.address.Address;
import com.jinshuo.mall.service.user.application.dto.AddressDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname AddressAssembler
 * @Description AddressAssembler模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class AddressAssembler {

    /**
     * 返回地址dto
     *
     * @param address
     * @return
     */
    public static AddressDto assembleAddressDto(Address address) {
        AddressDto dto = new AddressDto();
        if (null == address) {
            return null;
        }
        BeanUtils.copyProperties(address, dto);
        dto.setId(address.getAddressId().getId().toString());
        return dto;
    }
}
