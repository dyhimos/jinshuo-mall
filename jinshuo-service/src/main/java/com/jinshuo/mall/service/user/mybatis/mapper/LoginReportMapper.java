package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.loginreport.LoginReport;
import com.jinshuo.mall.service.user.application.qry.ManagerCountQry;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface LoginReportMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO login_report(" +
            "id,merchant_id,shop_id,member_count,access_count," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{loginReportId.id},#{merchantId},#{shopId},#{memberCount},#{accessCount}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(LoginReport loginReport);

    @Results(
            id = "loginReportResult",
            value = {
                    @Result(property = "loginReportId.id", column = "id"),
                    @Result(property = "merchantId", column = "merchant_id"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "memberCount", column = "member_count"),
                    @Result(property = "accessCount", column = "access_count"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM member_address WHERE status = 1 AND is_default = 0 AND mem_id=#{memId} LIMIT 1 ")
    LoginReport queryDefault(@Param("memId") Long memId);

    @Update("UPDATE  login_report SET member_count=#{memberCount},access_count=#{accessCount}," +
            "update_date =#{updateDate}" +
            " WHERE id = #{loginReportId.id}")
    int update(LoginReport loginReport);

    @ResultMap("loginReportResult")
    @Select("SELECT * FROM login_report WHERE shop_id=#{shopId} AND to_days( create_date ) = to_days(now()) ")
    LoginReport queryTodayReport(@Param("shopId") Long shopId);

    @ResultMap("loginReportResult")
    @SelectProvider(type = DynamicSql.class, method = "countLogin")
    List<LoginReport> countLogin(ManagerCountQry qry);

}
