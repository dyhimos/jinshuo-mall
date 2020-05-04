package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderAddress.GoodsOrderAddress;
import org.apache.ibatis.annotations.*;

/**
 * @Classname GoodsOrderAddressMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsOrderAddressMapper {

    /**
     * 根据订单id查询收货信息
     *
     * @param goodsOrderId
     * @return
     */
    @Results(
            id = "goodsOrderAddressList",
            value = {
                    @Result(property = "goodsOrderAddressId.id", column = "id"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "userName", column = "user_name"),
                    @Result(property = "userAddress", column = "user_address"),
                    @Result(property = "userPhone", column = "user_phone")
            }
    )
    @Select("SELECT * FROM goods_order_address WHERE order_id=#{id}")
    GoodsOrderAddress findByOrderId(GoodsOrderId goodsOrderId);

    /**
     * 保存订单收货信息
     *
     * @param goodsOrderAddress
     */
    @InsertProvider(type = OrderDynamicSql.class, method = "createGoodsOrderAddressSql")
    void save(GoodsOrderAddress goodsOrderAddress);

}
