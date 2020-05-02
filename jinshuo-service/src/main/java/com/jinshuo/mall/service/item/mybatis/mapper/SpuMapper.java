package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.spu.Spu;
import com.jinshuo.mall.service.item.application.dto.FrontSpuDto;
import com.jinshuo.mall.service.item.application.dto.TopicProductDto;
import com.jinshuo.mall.service.item.application.qry.SpuQry;
import com.jinshuo.mall.service.item.application.qry.TopicProductPageQry;
import com.jinshuo.mall.service.item.application.qry.TopicProductQry;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface SpuMapper {
    //@InsertProvider(type = SpuMapper.class, method = "createSql")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_spu(" +
            "id ,name,sketch ,category_id,shop_id ,type_id,brand_id,groud_id," +
            "tag,picture_url,spu_code,unit,price,market_price,cost_price,stock," +
            "warning_stock,virtual_sales,is_integral,integral,sort,goods_status,audit_status,is_sendcode,reserve_address," +
            "create_date,update_date,remarks,version," +
            "is_hot,supplier_id,poster,feature_id,merchant_id,services,single_sku,area_name,area_names )" +
            "VALUES (" +
            "#{spuId.id},#{name},#{sketch},#{categoryId},#{shopId.id},#{typeId},#{brandId.id},#{groudId.id}," +
            "#{tag},#{pictureUrl},#{spuCode},#{unit},#{price},#{marketPrice},#{costPrice},#{stock}," +
            "#{warningStock},#{virtualSales},#{isIntegral},#{integral},#{sort},#{goodsStatus},#{auditStatus},#{isSendcode},#{reserveAddress}," +
            "#{createDate},#{updateDate},#{remarks},#{version}," +
            "#{isHot},#{supplierId},#{poster},#{featureId},#{merchantId},#{services},#{singleSku},#{areaName},#{areaNames} )")
    void create(Spu spu);

    @Update("UPDATE  goods_spu SET " +
            " name= #{name},sketch= #{sketch},category_id= #{categoryId},shop_id = #{shopId.id}," +
            " type_id = #{typeId},brand_id= #{brandId.id},groud_id= #{groudId.id},tag =#{tag}," +
            " picture_url= #{pictureUrl},spu_code= #{spuCode}," +
            " unit =#{unit} ,price=#{price},market_price=#{marketPrice},cost_price=#{costPrice},stock=#{stock},warning_stock=#{warningStock}," +
            " virtual_sales=#{virtualSales},is_integral=#{isIntegral},integral=#{integral},sort=#{sort},goods_status=#{goodsStatus}," +
            "is_hot=#{isHot},is_sendcode=#{isSendcode},reserve_address=#{reserveAddress},supplier_id=#{supplierId}," +
            " audit_status=#{auditStatus},update_date=#{updateDate},remarks=#{remarks},version=#{version},poster=#{poster},feature_id=#{featureId}," +
            "services=#{services},single_sku=#{singleSku},area_name=#{areaName},area_names=#{areaNames}" +
            " WHERE id = #{spuId.id}")
    void update(Spu spu);

    @Update("UPDATE  goods_spu SET status=4" +
            " WHERE id = #{spuId}")
    int deleteById(@Param("spuId") Long spuId);

    @Results(
            id = "spuResult",
            value = {
                    @Result(property = "spuId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "sketch", column = "sketch"),
                    @Result(property = "categoryId", column = "category_id"),
                    @Result(property = "shopId.id", column = "shop_id"),
                    @Result(property = "merchantId", column = "merchant_id"),
                    @Result(property = "typeId", column = "type_id"),
                    @Result(property = "supplierId", column = "supplier_id"),
                    @Result(property = "brandId.id", column = "brand_id"),
                    @Result(property = "groudId.id", column = "groud_id"),
                    @Result(property = "tag", column = "tag"),
                    @Result(property = "pictureUrl", column = "picture_url"),
                    @Result(property = "spuCode", column = "spu_code"),
                    @Result(property = "unit", column = "unit"),
                    @Result(property = "price", column = "price"),
                    @Result(property = "marketPrice", column = "market_price"),
                    @Result(property = "costPrice", column = "cost_price"),
                    @Result(property = "stock", column = "stock"),
                    @Result(property = "warningStock", column = "warning_stock"),
                    @Result(property = "virtualSales", column = "virtual_sales"),
                    @Result(property = "realSales", column = "real_sales"),
                    @Result(property = "isIntegral", column = "is_integral"),
                    @Result(property = "integral", column = "integral"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "isHot", column = "is_hot"),
                    @Result(property = "isDis", column = "is_dis"),
                    @Result(property = "isSendcode", column = "is_sendcode"),
                    @Result(property = "reserveAddress", column = "reserve_address"),
                    @Result(property = "poster", column = "poster"),
                    @Result(property = "featureId", column = "feature_id"),
                    @Result(property = "areaName", column = "area_name"),
                    @Result(property = "areaNames", column = "area_names"),
                    @Result(property = "services", column = "services"),
                    @Result(property = "single_sku", column = "singleSku"),
                    @Result(property = "goodsStatus", column = "goods_status"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_spu WHERE status=1 AND id = #{spuId} LIMIT 1")
    Spu queryBySpuId(Long spuId);

    /**
     * 查询优品
     */
    @ResultMap("frontSpuResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "frontQueryExcellentSpuSql")
    List<FrontSpuDto> queryExcellent(SpuQry qry);

    @ResultMap("spuResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "querySpuSql")
    List<Spu> testFindAll(SpuQry qry);

    @ResultMap("spuResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "querySpuSqlNew")
    List<Spu> querySpuSqlNew(SpuQry qry);

    @Update("UPDATE  goods_spu SET stock=#{stock},goods_status=#{goodsStatus},real_sales=#{realSales},virtual_sales=#{virtualSales} " +
            " WHERE id = #{spuId.id}")
    int updateStock(Spu spu);

    @Update("UPDATE  goods_spu SET goods_status=#{goodsStatus},is_dis=1" +
            " WHERE id = #{spuId}")
    int upProduct(@Param("spuId") Long spuId, @Param("goodsStatus") Integer goodsStatus);

    @Update("UPDATE  goods_spu SET sort=#{sort}" +
            " WHERE id = #{spuId}")
    int updateSort(@Param("spuId") Long spuId, @Param("sort") Integer sort);

    @Update("UPDATE  goods_spu SET is_dis=#{isDis}" +
            " WHERE id = #{id}")
    int updateDis(@Param("id") Long id, @Param("isDis") Integer isDis);


    @Results(
            id = "spuTopicResult",
            value = {
                    @Result(property = "spuId", column = "spuId"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "sketch", column = "sketch"),
                    @Result(property = "categoryId", column = "categoryId"),
                    @Result(property = "shopId", column = "shopId"),
                    @Result(property = "tag", column = "tag"),
                    @Result(property = "pictureUrl", column = "pictureUrl"),
                    @Result(property = "spuCode", column = "spuCode"),
                    @Result(property = "price", column = "price"),
                    @Result(property = "marketPrice", column = "marketPrice"),
                    @Result(property = "stock", column = "stock"),
                    @Result(property = "virtualSales", column = "virtualSales"),
                    @Result(property = "realSales", column = "realSales"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "isHot", column = "isHot"),
                    @Result(property = "isDis", column = "isDis"),
                    @Result(property = "goodsStatus", column = "goodsStatus"),
                    @Result(property = "poster", column = "poster"),
                    @Result(property = "id", column = "id"),
            }
    )
    @SelectProvider(type = ItemDynamicSql.class, method = "queryTopicProduct")
    List<TopicProductDto> queryTopicProduct(TopicProductQry qry);

    @ResultMap("spuTopicResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "queryNotYetProductByFront")
    List<TopicProductDto> queryNotYetProductByFront(TopicProductPageQry qry);

    @ResultMap("spuTopicResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "queryTopicProductByFront")
    List<TopicProductDto> queryTopicProductByFront(TopicProductQry qry);


    @Results(
            id = "frontSpuResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "sketch", column = "sketch"),
                    @Result(property = "typeId", column = "type_id"),
                    @Result(property = "tag", column = "tag"),
                    @Result(property = "pictureUrl", column = "picture_url"),
                    @Result(property = "price", column = "price"),
                    @Result(property = "marketPrice", column = "market_price"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "isHot", column = "is_hot"),
                    @Result(property = "isDis", column = "is_dis"),
                    @Result(property = "poster", column = "poster"),
                    @Result(property = "featureId", column = "feature_id"),
                    @Result(property = "buyEndDate", column = "buy_end_date"),
                    @Result(property = "areaNames", column = "area_names"),
                    @Result(property = "activityAddress", column = "activity_address")
            }
    )
    @SelectProvider(type = ItemDynamicSql.class, method = "frontQuerySpuSql")
    List<FrontSpuDto> frontQuerySpuSql(SpuQry qry);


    /**
     * 查询分销产品
     */
    @ResultMap("frontSpuResult")
    @SelectProvider(type = ItemDynamicSql.class, method = "queryDis")
    List<FrontSpuDto> queryDis(SpuQry qry);

    /**
     * 根据参数查询souId
     */
    @ResultType(Long.class)
    @SelectProvider(type = ItemDynamicSql.class, method = "querySpuWithParam")
    List<Long> querySpuWithParam(SpuQry qry);


    @ResultType(Integer.class)
    @Select("SELECT count(1) FROM goods_spu WHERE shop_id=#{shopId} AND stock<1 AND status=1 ")
    int querySoldOutCount(@Param("shopId") Long shopId);
}
