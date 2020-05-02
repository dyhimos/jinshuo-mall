package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.cart.Cart;
import org.apache.ibatis.annotations.*;

/**
 * 购物车
 *
 * @Classname CartMapper
 * @Description
 * @Date 2019/9/16 20:09
 * @Created by dyh
 */
@Mapper
public interface CartMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO shopping_cart(id,user_id,shop_id,quantity,total_money," +
            "create_date,update_date,remarks) " +
            "VALUES(#{cartId.id},#{userId},#{shopId},#{quantity},#{totalMoney}," +
            "now(),now(),#{remarks})")
    int create(Cart cart);


    @Results(
            id = "cartResult",
            value = {
                    @Result(property = "cartId.id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "quantity", column = "quantity"),
                    @Result(property = "totalMoney", column = "total_money"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM shopping_cart WHERE status = 1 and user_id= #{userId}")
    Cart queryByUserId(@Param("userId") Long userId);

    @Update("UPDATE  shopping_cart SET quantity=#{quantity},total_money=#{totalMoney}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{cartId.id}")
    int update(Cart cart);
}
