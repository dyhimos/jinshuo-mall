package com.jinshuo.mall.service.finance.mybatis.mapper;

import com.jinshuo.mall.domain.finance.FinanceRecharge;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
@Mapper
public interface FinanceRechargeMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into finance_recharge (" +
            "id, shop_id,sn,mem_id,type,amount,create_date,update_date,remarks)" +
            "values (#{financeRechargeId.id},#{shopId},#{sn},#{memId},#{type},#{amount},#{createDate},#{updateDate},#{remarks})")
    int create(FinanceRecharge financeRecharge);


    @Results(
            id = "accountRechargeResult",
            value = {
                    @Result(column="id" , property="financeRechargeId.id" ),
                    @Result(column="shop_id" , property="shopId" ),
                    @Result(column="sn" , property="sn" ),
                    @Result(column="mem_id" , property="memId" ),
                    @Result(column="type" , property="type" ),
                    @Result(column="amount" , property="amount" ),
                    @Result(column="create_date" ,property="createDate" ),
                    @Result(column="update_date" ,property="updateDate" ),
                    @Result(column="version" , property="version" ),
                    @Result(column="status" , property="status.code" ),
                    @Result(column="remarks" , property="remarks" )
            }
    )
    @Select("SELECT * FROM finance_recharge WHERE status = 1 AND id=#{memId} ORDER BY create_date DESC ")
    List<FinanceRecharge> queryByMemberId(@Param("memId") Long memId);


    @Update("UPDATE  finance_recharge SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}
