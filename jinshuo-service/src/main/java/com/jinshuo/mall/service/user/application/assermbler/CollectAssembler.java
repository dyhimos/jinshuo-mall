package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.service.item.application.dto.SpuDto;
import com.jinshuo.mall.service.user.application.dto.UserCollectSpuDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname GoodsOrderAssembler
 * @Description GoodsOrderAssembler模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class CollectAssembler {

    /**
     * 返回订单列表
     *
     * @param spuDto
     * @return
     */
    public static UserCollectSpuDto assembleUserCollectSpuDto(SpuDto spuDto, Long id) {
        UserCollectSpuDto dto = new UserCollectSpuDto();
        dto.setId(id.toString());
        if (spuDto == null) {
            return dto;
        }
        BeanUtils.copyProperties(spuDto, dto);
        dto.setSpuId(spuDto.getId());
        dto.setId(id.toString());
        return dto;
    }
}
