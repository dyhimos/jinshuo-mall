package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.supplier.SupplierManager;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface SupplierManagerMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO supplier_manager(" +
            "id,supplier_id,name,mobile,id_card,role,sex,user_account_id," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{supplierManagerId.id},#{supplierId},#{name},#{mobile},#{idCard},#{role},#{sex},#{userAccountId}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(SupplierManager supplierManager);

    @Results(
            id = "supplierManagerResult",
            value = {
                    @Result(property = "supplierManagerId.id", column = "id"),
                    @Result(property = "supplierId", column = "supplier_id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "mobile", column = "mobile"),
                    @Result(property = "idCard", column = "id_card"),
                    @Result(property = "role", column = "role"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "userAccountId", column = "user_account_id"),
                    @Result(property = "supplierManagerStatus", column = "supplier_manager_status"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM supplier_manager WHERE status = 1 AND id=#{supplierManagerId} LIMIT 1 ")
    SupplierManager queryById(@Param("supplierManagerId") Long supplierManagerId);

    @Update("UPDATE  supplier_manager SET name=#{name},mobile=#{mobile}," +
            "id_card=#{idCard},role=#{role},sex=#{sex},update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{supplierManagerId.id}")
    int update(SupplierManager supplierManager);

    @ResultMap("supplierManagerResult")
    @Select("SELECT * FROM supplier_manager WHERE status=1 AND supplier_id=#{supplierId} ORDER BY create_date DESC ")
    List<SupplierManager> findAll(@Param("supplierId") Long supplierId);

    @Update("UPDATE  supplier_manager SET status=4 " +
            " WHERE  id=#{supplierManagerId} ")
    int delete(@Param("supplierManagerId") Long supplierManagerId);

    @ResultMap("supplierManagerResult")
    @Select("SELECT * FROM supplier_manager WHERE status=1 AND user_account_id=#{userAccountId}  ")
    SupplierManager queryByUserAccountId(@Param("userAccountId") Long userAccountId);

}
