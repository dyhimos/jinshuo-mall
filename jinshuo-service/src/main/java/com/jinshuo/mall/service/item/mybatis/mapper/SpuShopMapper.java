package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.spushop.SpuShop;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SpuShopMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spu_shop(id,spu_id,shop_id,create_date,update_date,remarks) " +
            "VALUES(#{spuShopId.id},#{spuId},#{shopId},#{createDate},#{updateDate},#{remarks})")
    void create(SpuShop spuShop);

    @Results(
            id = "spushopResult",
            value = {
                    @Result(property = "spuShopId.id", column = "id"),
                    @Result(property = "spuId", column = "spu_id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spu_shop WHERE status = 1 AND shop_id=#{shopId}")
    List<SpuShop> queryByShopId(@Param("shopId") Long shopId);


    @Update("UPDATE  goods_spu_shop SET status=4 " +
            " WHERE id = #{spuShopId}")
    int delete(@Param("spuShopId") Long spuShopId);

    @ResultMap("spushopResult")
    @Select("SELECT * FROM goods_spu_shop WHERE status = 1 AND shop_id=#{shopId} and spu_id=#{spuId} LIMIT 1")
    SpuShop findByShopIdSpuId(@Param("shopId") Long shopId, @Param("spuId") Long spuId);
}
