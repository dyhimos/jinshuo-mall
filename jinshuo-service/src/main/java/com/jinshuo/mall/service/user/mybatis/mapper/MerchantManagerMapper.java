package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.merchantlogin.MerchantManager;
import org.apache.ibatis.annotations.*;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/9/16 20:09
 * @Created by dyh
 */
@Mapper
public interface MerchantManagerMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO merchant_manager(" +
            "id,merchant_id,type,name,mobile,id_card,sex,user_id" +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{merchantManagerId.id},#{merchantId.id},#{type},#{name},#{idCard},#{sex},#{userAccountId.id}," +
            "#{createDate},#{updateDate},#{remarks})")
    int save(MerchantManager merchantManager);

    @Results(
            id = "merchantManagerResult",
            value = {
                    @Result(property = "merchantManagerId.id", column = "id"),
                    @Result(property = "merchantId.id", column = "merchant_id"),
                    @Result(property = "userAccountId.id", column = "user_id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "mobile", column = "mobile"),
                    @Result(property = "idCard", column = "id_card"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM merchant_manager WHERE status = 1 AND id=#{id} LIMIT 1 ")
    MerchantManager queryById(@Param("id") Long id);


    @ResultMap("merchantManagerResult")
    @Select("SELECT * FROM merchant_manager WHERE status=1 AND user_id=#{userAccountId} LIMIT 1 ")
    MerchantManager queryByUserId(@Param("userAccountId") Long userAccountId);
}
