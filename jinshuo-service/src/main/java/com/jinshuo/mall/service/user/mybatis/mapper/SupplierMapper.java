package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.supplier.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface SupplierMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO supplier(" +
            "id,user_id,supplier_code,icon_url,supplier_name," +
            "supplier_type,link_man,phone_number,province,city," +
            "disc,address,`desc`,supplier_status,shop_id,login_flag," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{supplierId.id},#{userId},#{supplierCode},#{iconUrl},#{supplierName}," +
            "#{supplierType},#{linkMan},#{phoneNumber},#{province},#{city}," +
            "#{disc},#{address},#{desc},#{supplierStatus},#{shopId},#{loginFlag}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(Supplier supplier);

    @Results(
            id = "supplierResult",
            value = {
                    @Result(property = "supplierId.id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "supplierCode", column = "supplier_code"),
                    @Result(property = "iconUrl", column = "icon_url"),
                    @Result(property = "supplierName", column = "supplier_name"),
                    @Result(property = "supplierType", column = "supplier_type"),
                    @Result(property = "linkMan", column = "link_man"),
                    @Result(property = "phoneNumber", column = "phone_number"),
                    @Result(property = "province", column = "province"),
                    @Result(property = "city", column = "city"),
                    @Result(property = "disc", column = "disc"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "desc", column = "desc"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "loginFlag", column = "login_flag"),
                    @Result(property = "supplierStatus", column = "supplier_status"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM supplier WHERE status = 1 AND id=#{id} LIMIT 1 ")
    Supplier queryById(@Param("id") Long id);

    @Update("UPDATE  supplier SET status=4 " +
            " WHERE id = #{id} ")
    int delete(@Param("id") Long id);


    @ResultMap("supplierResult")
    @Select("SELECT * FROM supplier WHERE status=1  AND supplier_status=0 AND shop_id=#{shopId} ORDER BY create_date DESC ")
    List<Supplier> queryList(Supplier supplier);


    @Update("UPDATE  supplier SET  icon_url=#{iconUrl},supplier_name=#{supplierName}," +
            "supplier_type=#{supplierType},link_man=#{linkMan},phone_number=#{phoneNumber},province=#{province}," +
            "city=#{city},disc=#{disc},address=#{address},`desc`=#{desc},shop_id=#{shopId},login_flag=#{loginFlag}" +
            " WHERE id = #{supplierId.id} ")
    int update(Supplier supplier);


    @ResultMap("supplierResult")
    @Select("SELECT * FROM supplier WHERE status=1  AND shop_id=#{shopId} ORDER BY create_date DESC ")
    List<Supplier> queryPageList(Supplier supplier);


}
