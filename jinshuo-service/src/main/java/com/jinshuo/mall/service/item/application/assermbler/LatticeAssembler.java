package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.lattice.Lattice;
import com.jinshuo.mall.service.item.application.dto.LatticeDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/7/22.
 */
public class LatticeAssembler {

    /**
     * @param lattice
     * @return
     */
    public static LatticeDto assembleDto(Lattice lattice) {
        if (null == lattice) {
            return null;
        }
        LatticeDto dto = new LatticeDto();
        BeanUtils.copyProperties(lattice, dto);
        dto.setId(lattice.getLatticeId().getId().toString());
        if (null != lattice.getShopId()) {
            dto.setShopId(lattice.getShopId().toString());
        }
        return dto;
    }

}
