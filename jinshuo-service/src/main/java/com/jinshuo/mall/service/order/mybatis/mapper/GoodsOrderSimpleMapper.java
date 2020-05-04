package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.order.product.orderSimple.GoodsOrderSimple;
import com.jinshuo.mall.service.order.application.qry.GoodsSimpleQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 寄样信息
 *
 * @Classname GoodsOrderSimpleMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsOrderSimpleMapper {


    /**
     * 根据商家查询样品订单列表
     *
     * @param goodsSimpleQry
     * @return
     */

    @Results(
            id = "goodsOrderSampleList",
            value = {
                    @Result(property = "goodsOrderSimpleId.id", column = "id"),
                    @Result(property = "sampleInfo", column = "sample_info"),
                    @Result(property = "sampleNo", column = "sample_no"),
                    @Result(property = "memberId", column = "member_id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "userName", column = "user_name"),
                    @Result(property = "userAddress", column = "user_address"),
                    @Result(property = "userPhone", column = "user_phone"),
                    @Result(property = "sampleStatus", column = "sample_status"),
                    @Result(property = "expressCompany", column = "express_company"),
                    @Result(property = "expressNo", column = "express_no"),
                    @Result(property = "expressDate", column = "express_date"),
                    @Result(property = "expressCode", column = "express_code")
            }
    )
    @SelectProvider(type = OrderDynamicSql.class, method = "queryGoodsSimpleOrderSql")
    List<GoodsOrderSimple> findList(GoodsSimpleQry goodsSimpleQry);

    @ResultMap("goodsOrderSampleList")
    @Select("select * from goods_order_sample where sample_no=#{sampleNo}")
    GoodsOrderSimple findBySampleNo(String sampleNo);

    @ResultMap("goodsOrderSampleList")
    @Select("select * from goods_order_sample where id=#{id}")
    GoodsOrderSimple findById(Long id);

    /**
     * 保存寄样信息
     *
     * @param goodsOrderSimple
     */
    @Insert("INSERT INTO goods_order_sample(" +
            "id,sample_info,sample_no,member_id,shop_id,user_name,user_address,user_phone,sample_status," +
            "express_company,express_no,express_date,express_code," +
            "create_date,update_date,remarks,status,version) " +
            "VALUES(" +
            "#{goodsOrderSimpleId.id},#{sampleInfo},#{sampleNo},#{memberId},#{shopId},#{userName},#{userAddress},#{userPhone},#{sampleStatus}," +
            "#{expressCompany},#{expressNo},#{expressDate},#{expressCode}," +
            "#{createDate},#{updateDate},#{remarks},#{status.code},#{version})")
    void save(GoodsOrderSimple goodsOrderSimple);

    /**
     * 更新寄样产品状态
     *
     * @param id
     * @param sampleStatus
     */
    @Update("UPDATE  goods_order_sample SET simple_status=#{sampleStatus} " +
            " WHERE id = #{id} ")
    void updateSimpleStatus(@Param("id") Long id, @Param("sampleStatus") Integer sampleStatus);

    /**
     * 更新寄样产品快递信息
     *
     * @param goodsOrderSimple
     */
    @Update("UPDATE  goods_order_sample SET  " +
            "sample_status=2," +
            "express_company=#{expressCompany}," +
            "express_no=#{expressNo}," +
            "express_date=#{expressDate}," +
            "express_code=#{expressCode}" +
            " WHERE id = #{goodsOrderSimpleId.id} ")
    void upSampleOrderExpress(GoodsOrderSimple goodsOrderSimple);

    /**
     * 删除
     *
     * @param id
     */
    @Update("UPDATE  goods_order_sample SET status=4  WHERE id = #{id} ")
    void delete(@Param("id") Long id);
}
