package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCode;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname GoodsOrderMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsVerificationCodeMapper {

    @InsertProvider(type = DynamicSql.class, method = "createVerificationCodeSql")
    void save(OrderVerificationCode orderVerificationCode);


    /**
     * 根据订单详情查询核销码信息
     *
     * @param orderDetailId 订单详情id
     * @return
     */
    @Results(
            id = "orderVerificationCode",
            value = {
                    @Result(property = "orderVerificationCodeId.id", column = "id"),
                    @Result(property = "goodsOrderId.id", column = "order_id"),
                    @Result(property = "goodsOrderDetailId.id", column = "order_detail_id"),
                    @Result(property = "verifySn", column = "verify_sn"),
                    @Result(property = "qrCode", column = "qr_code"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "isUse", column = "is_use"),
                    @Result(column = "id", property = "verificationLogList", javaType = List.class,
                            many = @Many(select = "com.ym.wool.oc.infra.mybatis.mapper.GoodsVerificationCodeLogMapper.selectByVerificationId")),
            }
    )
    @Select("SELECT * FROM order_verification_code WHERE order_detail_id=#{orderDetailId}")
    List<OrderVerificationCode> selectByOrderDetailId(Long orderDetailId);

    @ResultMap("orderVerificationCode")
    @Select("SELECT * FROM order_verification_code WHERE status=1 AND verify_sn=#{verifySn}")
    OrderVerificationCode queryByverifySn(String verifySn);


    @Update("UPDATE order_verification_code  SET is_use=0 WHERE id=#{orderVerificationCodeId}")
    int useVerificationCode(@Param("orderVerificationCodeId") Long orderVerificationCodeId);


    @ResultMap("orderVerificationCode")
    @Select("SELECT * FROM order_verification_code WHERE status=1 AND order_id=#{orderId}")
    List<OrderVerificationCode> queryOrderId(Long orderId);
}
