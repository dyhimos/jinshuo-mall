package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.domain.user.model.merchant.Merchant;
import org.apache.ibatis.annotations.*;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface MerchantMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO merchant(" +
            "id,user_id,type,name,link_man,link_phone," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{merchantId.id},#{userAccountId.id},#{type},#{name},#{linkMan},#{linkPhone}," +
            "now(),now(),#{remarks})")
    int create(Merchant merchant);

    @Results(
            id = "merchantResult",
            value = {
                    @Result(property = "merchantId.id", column = "id"),
                    @Result(property = "userAccountId.id", column = "user_id"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "linkMan", column = "link_man"),
                    @Result(property = "linkPhone", column = "link_phone"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM merchant WHERE status = 1 AND id=#{merchantId} LIMIT 1 ")
    Merchant queryById(@Param("merchantId") Long merchantId);


    @ResultMap("merchantResult")
    @Select("SELECT * FROM merchant WHERE status = 1 AND user_id=#{userId} LIMIT 1 ")
    Merchant findByUserId(@Param("userId") Long userId);


    @Update("UPDATE  merchant SET type=#{type} ," +
            "name=#{name},link_man=#{linkMan},link_phone=#{linkPhone}," +
            "update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{menuId.id}")
    int update(Menu menu);

}
