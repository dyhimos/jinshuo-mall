package com.jinshuo.mall.service.finance.mybatis.mapper;

import com.jinshuo.mall.domain.finance.FinanceAccountCash;
import com.jinshuo.mall.service.finance.application.dto.FinanceCashPageDto;
import com.jinshuo.mall.service.finance.application.qry.FinanceAccountCashQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
@Mapper
public interface FinanceAccountCashMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into finance_account_cash (" +
            "id, shop_id,mem_id,avaible_amount,total_amount,frozen_amount,recharge_amount,recharge_time,account_status,add_time," +
            " create_date,update_date,remarks) " +
            "values (#{financeAccountCashId.id},#{shopId}, #{memId},#{avaibleAmount},#{totalAmount},#{frozenAmount},#{rechargeAmount}," +
            "#{rechargeTime},#{accountStatus},#{addTime},#{createDate}, #{updateDate}, #{remarks})")
    int create(FinanceAccountCash financeAccountCash);


    @Results(
            id = "accountResult",
            value = {
                    @Result(column = "id", property = "financeAccountCashId.id"),
                    @Result(column = "shop_id", property = "shopId"),
                    @Result(column = "mem_id", property = "memId"),
                    @Result(column = "avaible_amount", property = "avaibleAmount"),
                    @Result(column = "total_amount", property = "totalAmount"),
                    @Result(column = "frozen_amount", property = "frozenAmount"),
                    @Result(column = "recharge_amount", property = "rechargeAmount"),
                    @Result(column = "recharge_time", property = "rechargeTime"),
                    @Result(column = "account_status", property = "accountStatus"),
                    @Result(column = "add_time", property = "addTime"),
                    @Result(column = "create_date", property = "createDate"),
                    @Result(column = "update_date", property = "updateDate"),
                    @Result(column = "version", property = "version"),
                    @Result(column = "remarks", property = "remarks"),
                    @Result(column = "status", property = "status.code")
            }
    )
    @Select("SELECT * FROM finance_account_cash WHERE status = 1 AND mem_id=#{memId} LIMIT 1")
    FinanceAccountCash queryByMemberId(@Param("memId") Long memId);


    @Update("UPDATE  finance_account_cash SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("UPDATE  finance_account_cash SET avaible_amount=#{avaibleAmount},total_amount=#{totalAmount},recharge_time=#{rechargeTime},  " +
            "add_time=#{addTime} WHERE id = #{financeAccountCashId.id}")
    int recharge(FinanceAccountCash financeAccountCash);


    /**
     * 查询资金账户的列表
     * @param query
     * @return
     */
    @Results(
            id = "accountListResult",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "mem_id", property = "memId"),
                    @Result(column = "avaible_amount", property = "avaibleAmount"),
                    @Result(column = "total_amount", property = "totalAmount"),
                    @Result(column = "frozen_amount", property = "frozenAmount"),
                    @Result(column = "recharge_amount", property = "rechargeAmount"),
                    @Result(column = "recharge_time", property = "rechargeTime"),
                    //会员昵称
                    @Result(column = "nickname", property = "nickname"),
                    //会员编号
                    @Result(column = "mem_no", property = "memNo")
            }
    )
    @Select("<script>" +
            "SELECT a.*,b.nickname,b.mem_no   FROM finance_account_cash a left join member b on a.mem_id = b.user_id where 1=1 " +
            "<if test='memNo != null and memNo!=\"\" '> " +
            "AND b.mem_no = #{memNo}" +
            "</if>" +
            "<if test='nickname != null and nickname!=\"\" '> " +
            "AND b.nickname = #{nickname}" +
            "</if>" +
            " ORDER BY a.create_date  Desc " +
            "</script>")
    List<FinanceCashPageDto> queryFinanceAccountList(FinanceAccountCashQry query);
}