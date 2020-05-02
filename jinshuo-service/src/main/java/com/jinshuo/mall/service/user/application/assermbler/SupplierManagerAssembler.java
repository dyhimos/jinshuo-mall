package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.supplier.SupplierManager;
import com.jinshuo.mall.service.user.application.dto.SupplierManagerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class SupplierManagerAssembler {

    /**
     * 返回订单列表
     *
     * @param supplierManager
     * @return
     */
    public static SupplierManagerDto assembleDto(SupplierManager supplierManager) {
        SupplierManagerDto dto = new SupplierManagerDto();
        if (supplierManager == null) {
            return dto;
        }
        BeanUtils.copyProperties(supplierManager, dto);
        dto.setId(supplierManager.getSupplierManagerId().getId());
        return dto;
    }
}
