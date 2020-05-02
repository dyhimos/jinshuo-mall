package com.jinshuo.mall.service.order.application.assermbler;

import com.jinshuo.mall.domain.order.product.orderSimple.GoodsOrderSimple;
import com.jinshuo.mall.service.order.application.dto.GoodsSimpleOrderListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname GoodsSimpleOrderAssembler
 * @Description GoodsSimpleOrderAssembler，完成domain model对象到dto的转换，组装职责包括：
 *                1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 *                2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class GoodsSimpleOrderAssembler {

    /**
     * 返回订单列表
     * @param goodsOrderSimple
     * @return
     */
    public static GoodsSimpleOrderListDto assembleGoodsOrderDto(GoodsOrderSimple goodsOrderSimple) {
        if(goodsOrderSimple == null) {
            return null;
        }
        GoodsSimpleOrderListDto goodsSimpleOrderListDto=new GoodsSimpleOrderListDto();
        BeanUtils.copyProperties(goodsOrderSimple,goodsSimpleOrderListDto);
        goodsSimpleOrderListDto.setId(goodsOrderSimple.getGoodsOrderSimpleId().getId());
        return goodsSimpleOrderListDto;
    }
}
