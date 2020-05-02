package com.jinshuo.mall.service.order.application.assermbler;

import com.jinshuo.mall.domain.order.product.order.GoodsOrder;
import com.jinshuo.mall.domain.order.product.orderDetail.GoodsOrderDetail;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderAddressDto;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderDetailDto;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderListDto;
import com.jinshuo.mall.service.order.application.dto.GoodsOrderStatusDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname GoodsOrderAssembler
 * @Description GoodsOrderAssembler模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class GoodsOrderAssembler {

    /**
     * 返回订单列表
     *
     * @param goodsOrder
     * @return
     */
    public static GoodsOrderListDto assembleGoodsOrderDto(GoodsOrder goodsOrder) {
        if (goodsOrder == null) {
            return null;
        }
        GoodsOrderListDto goodsOrderDto = new GoodsOrderListDto();
        BeanUtils.copyProperties(goodsOrder, goodsOrderDto);
        goodsOrderDto.setId(goodsOrder.getGoodsOrderId().getId());
        goodsOrderDto.setCreateDate(goodsOrder.getCreateDate());
        //如果为实物
        GoodsOrderStatusDto orderStatus = new GoodsOrderStatusDto();
        orderStatus.setCode(goodsOrder.getOrderStatus().getCode());
        orderStatus.setValue(goodsOrder.getOrderStatus().getCode());
        orderStatus.setDesc(goodsOrder.getOrderStatus().getDesc());
        orderStatus.setDisplayName(goodsOrder.getOrderStatus().getDesc());
        if (1 != goodsOrder.getOrderType()) {
            switch (goodsOrder.getOrderStatus().getCode()) {
                case 1:
                    orderStatus.setDesc("待发码");
                    orderStatus.setDisplayName("待发码");
                    break;
                case 2:
                    orderStatus.setDesc("待使用");
                    orderStatus.setDisplayName("待使用");
                    break;
                case 4:
                    orderStatus.setDesc("已核销");
                    orderStatus.setDisplayName("已核销");
                    break;
                default:
            }
        }
        //设置状态
        goodsOrderDto.setOrderStatus(orderStatus);
        if (goodsOrder.getGoodsOrderAddress() != null) {
            GoodsOrderAddressDto goodsOrderAddressDto = new GoodsOrderAddressDto();
            BeanUtils.copyProperties(goodsOrder.getGoodsOrderAddress(), goodsOrderAddressDto);
            goodsOrderDto.setGoodsOrderAddress(goodsOrderAddressDto);
        }

        List<GoodsOrderDetailDto> goodsOrderDetailDtoList = new ArrayList<>();
        for (GoodsOrderDetail goodsOrderDetail : goodsOrder.getGoodsOrderDetailList()) {
            GoodsOrderDetailDto goodsOrderDetailDto = new GoodsOrderDetailDto();
            BeanUtils.copyProperties(goodsOrderDetail, goodsOrderDetailDto);
            if (goodsOrderDetail.getVerificationCodeList() != null) {
                goodsOrderDetailDto.setOrderVerificationCodeList(goodsOrderDetail.getVerificationCodeList());
            }
            goodsOrderDetailDtoList.add(goodsOrderDetailDto);
        }

        goodsOrderDto.setGoodsOrderDetailList(goodsOrderDetailDtoList);

        return goodsOrderDto;
    }
}
