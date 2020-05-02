package com.jinshuo.mall.service.order.application.assermbler;

import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import com.jinshuo.mall.service.order.application.cmd.GoodsOrderAddressCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname GoodsOrderAddressAssembler
 * @Description GoodsOrderAddressAssembler转换器，完成domain model对象到dto的转换，组装职责包括：
 *                1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 *                2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class GoodsOrderAddressAssembler {

    /**
     * 将保存参数转换为对象
     * @param goodsOrderAddressCmd
     * @return
     */
    public static GoodsOrderAddress assembleGoodsOrderAddress(GoodsOrderAddressCmd goodsOrderAddressCmd) {
        if(goodsOrderAddressCmd == null) {
            return null;
        }
        GoodsOrderAddress goodsOrderAddress=new GoodsOrderAddress();
        BeanUtils.copyProperties(goodsOrderAddressCmd,goodsOrderAddress);
        return goodsOrderAddress;
    }
}
