package com.jinshuo.mall.service.order.mybatis.mapper;

import com.jinshuo.mall.domain.order.product.orderVerificationCode.OrderVerificationCodeLog;
import com.jinshuo.mall.service.order.application.qry.LogQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname GoodsOrderMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface GoodsVerificationCodeLogMapper {

    @Insert("INSERT INTO order_verification_code_log(id,order_id,order_detail_id,verify_sn,user_account_id," +
            "name,err_code,err_msg,supplier_id,verification_id," +
            "create_date,update_date,remarks) " +
            "VALUES(#{orderVerificationCodeLogId.id},#{goodsOrderId},#{goodsOrderDetailId},#{verifySn},#{userAccountId}," +
            "#{name},#{errCode},#{errMsg},#{supplierId},#{verificationId}," +
            "now(),now(),#{remarks})")
    int save(OrderVerificationCodeLog orderVerificationCodeLog);


    /**
     * 根据供应商id查询核销记录
     *
     * @param supplierId 供应商id
     * @return
     */
    @Results(
            id = "logResult",
            value = {
                    @Result(property = "orderVerificationCodeLogId.id", column = "id"),
                    @Result(property = "verificationId", column = "verification_id"),
                    @Result(property = "supplierId", column = "supplier_id"),
                    @Result(property = "goodsOrderId", column = "order_id"),
                    @Result(property = "goodsOrderDetailId", column = "order_detail_id"),
                    @Result(property = "verifySn", column = "verify_sn"),
                    @Result(property = "userAccountId", column = "user_account_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "errCode", column = "err_code"),
                    @Result(property = "errMsg", column = "err_msg"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM order_verification_code_log WHERE supplier_id=#{supplierId} ORDER BY create_date DESC")
    List<OrderVerificationCodeLog> selectBySupplierId(Long supplierId);

    @ResultMap("logResult")
    @Select("SELECT * FROM order_verification_code_log WHERE id=#{id} ")
    OrderVerificationCodeLog selectById(Long id);

    @ResultMap("logResult")
    @Select("SELECT * FROM order_verification_code_log WHERE verification_id=#{verificationId} ")
    List<OrderVerificationCodeLog> selectByVerificationId(@Param("verificationId") Long verificationId);

    @ResultMap("logResult")
    @Select("SELECT  " +
            "o.id as  id ," +
            "o.verification_id as  verification_id ," +
            "o.supplier_id as  supplier_id ," +
            "o.order_id as  order_id ," +
            "o.order_detail_id as  order_detail_id ," +
            "o.verify_sn as  verify_sn ," +
            "o.user_account_id as  userAccount_id ," +
            "o.name as  name ," +
            "o.err_code as  err_code ," +
            "o.err_msg as  err_msg ," +
            "o.status as  status ," +
            "o.version as  version ," +
            "o.remarks as  remarks ," +
            "o.create_date as create_date " +
            "FROM " +
            "order_verification_code_log o LEFT JOIN goods_order_detail od " +
            "ON o.order_detail_id = od.id " +
            "WHERE od.goods_spu_id=#{spuId} ORDER BY o.create_date DESC ")
    List<OrderVerificationCodeLog> selectBySpuId(LogQry qry);
}
