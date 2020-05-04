package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.product.order.GoodsOrderId;
import com.jinshuo.mall.domain.order.product.orderExpress.GoodsOrderExpress;
import org.apache.ibatis.annotations.*;

/**
 * 快递mapper
 *
 * @Classname GoodsExpressMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsExpressMapper {

    @InsertProvider(type = OrderDynamicSql.class, method = "createGoodsExpressSql")
    void save(GoodsOrderExpress goodsOrderExpress);


    @Results(
            id = "goodsExpress",
            value = {
                    @Result(property = "goodsOrderExpressId.id", column = "id"),
                    @Result(property = "goodsOrderId.id", column = "order_id"),
                    @Result(property = "expressCompanyName", column = "express_company_name"),
                    @Result(property = "expressNo", column = "express_no"),
                    @Result(property = "expressCode", column = "express_code"),
                    @Result(property = "createDate", column = "create_date")
            }
    )
    @Select("SELECT * FROM goods_express WHERE order_id=#{id}")
    GoodsOrderExpress findGoodsExpressById(GoodsOrderId goodsOrderId);

}
