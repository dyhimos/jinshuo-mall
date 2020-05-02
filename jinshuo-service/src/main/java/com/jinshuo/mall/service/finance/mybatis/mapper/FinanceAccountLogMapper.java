package com.jinshuo.mall.service.finance.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.finance.FinanceAccountLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 19458 on 2019/12/25.
 */
@Mapper
public interface FinanceAccountLogMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into finance_account_log (" +
            "id,shop_id,mem_id,type,source,source_sn,before_change_amount,after_change_amount,change_amount,`desc`," +
            "update_date,create_date,remarks) " +
            " values (#{financeAccountLogId.id},#{shopId},#{memId},#{type},#{source},#{sourceSn},#{beforeChangeAmount},#{afterChangeAmount},#{changeAmount},#{desc}," +
            "#{updateDate},#{createDate},#{remarks})")
    int create(FinanceAccountLog financeAccountLog);


    @Results(
            id = "accountLogResult",
            value = {
                    @Result(column="id" , property="financeAccountLogId.id" ),
                    @Result(column="shop_id" , property="shopId" ),
                    @Result(column="mem_id" , property="memId" ),
                    @Result(column="type" , property="type" ),
                    @Result(column="source" , property="source" ),
                    @Result(column="source_sn" , property="sourceSn" ),
                    @Result(column="before_change_amount" , property="beforeChangeAmount" ),
                    @Result(column="after_change_amount" , property="afterChangeAmount" ),
                    @Result(column="change_amount" , property="changeAmount" ),
                    @Result(column="desc" , property="desc" ),
                    @Result(column="update_date" , property="updateDate" ),
                    @Result(column="create_date" , property="createDate" ),
                    @Result(column="version" , property="version" ),
                    @Result(column="remarks" , property="remarks" ),
                    @Result(property = "status", column = "status",javaType = Status.class,typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("SELECT * FROM finance_account_log WHERE status = 1 AND mem_id=#{memId} ORDER BY create_date DESC")
    List<FinanceAccountLog> queryByMemberId(@Param("memId") Long memId);


    @ResultMap(value="accountLogResult")
    @Select("<script>" +
            "SELECT * FROM finance_account_log WHERE status = 1 " +
            "<if test='memId != null'> " +
            "AND mem_id = #{memId}" +
            "</if>" +
            "<if test='type != null'> " +
            "AND type = #{type}" +
            "</if>" +
            "<if test='source != null'> " +
            "AND source = #{source}" +
            "</if>" +
            "ORDER BY create_date DESC " +
            "</script>")
    List<FinanceAccountLog> findAccountRechargeLogs(FinanceAccountLog financeAccountLog);

    @Update("UPDATE  finance_account_log SET status=4  WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}
