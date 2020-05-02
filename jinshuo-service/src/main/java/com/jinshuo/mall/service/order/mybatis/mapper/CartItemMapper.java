package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.cart.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 购物车
 *
 * @Classname CartMapper
 * @Description
 * @Date 2019/9/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CartItemMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO shopping_cart_item(id,cart_id,sku_id,goods_price,add_price,quantity,add_time," +
            "create_date,update_date,remarks) " +
            "VALUES(#{cartItemId.id},#{cartId.id},#{skuId},#{goodsPrice},#{addPrice},#{quantity},#{addTime}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(CartItem cartItem);


    @Results(
            id = "cartItemResult",
            value = {
                    @Result(property = "cartItemId.id", column = "id"),
                    @Result(property = "cartId.id", column = "cart_id"),
                    @Result(property = "skuId", column = "sku_id"),
                    @Result(property = "goodsPrice", column = "goods_price"),
                    @Result(property = "addPrice", column = "add_price"),
                    @Result(property = "quantity", column = "quantity"),
                    @Result(property = "addTime", column = "add_time"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM shopping_cart_item WHERE status = 1 and cart_id= #{cartId} ORDER BY create_date DESC ")
    List<CartItem> queryBycartId(@Param("cartId") Long cartId);

    @Update("UPDATE  shopping_cart_item SET cart_id=#{cartId.id},sku_id=#{skuId},goods_price=#{goodsPrice},add_price=#{addPrice}," +
            "quantity=#{quantity},quantity=#{quantity},add_time=#{addTime}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{cartItemId.id}")
    int update(CartItem cartItem);

    @Update("UPDATE  shopping_cart_item SET status=4 " +
            " WHERE id = #{id}")
    int delete(@Param("id") Long id);

    @ResultMap("cartItemResult")
    @Select("SELECT * FROM shopping_cart_item WHERE status = 1 and cart_id= #{cartId} and sku_id= #{skuId} " +
            "ORDER BY create_date DESC LIMIT 1")
    CartItem queryBySkuId(@Param("cartId") Long cartId, @Param("skuId") Long skuId);


    @Update("UPDATE  shopping_cart_item SET quantity=#{quantity} " +
            " WHERE id = #{cartItemId.id}")
    int minus(CartItem cartItem);

    @ResultMap("cartItemResult")
    @Select("SELECT * FROM shopping_cart_item WHERE status = 1 and id= #{id}  " +
            "  LIMIT 1")
    CartItem queryById(@Param("id") Long id);

    @ResultMap("cartItemResult")
    @Select("SELECT * FROM shopping_cart_item WHERE status = 1 and cart_id= #{cartId}  " +
            " ORDER BY create_date DESC ")
    List<CartItem> queryCartId(@Param("cartId") Long cartId);
}
