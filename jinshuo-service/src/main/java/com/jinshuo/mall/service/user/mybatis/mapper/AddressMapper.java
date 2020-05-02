package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.address.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface AddressMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO member_address(" +
            "id,mem_id,province,city,districts,address,receiver,phone,is_default," +
            "create_date,update_date,remarks) " +
            "VALUES(" +
            "#{addressId.id},#{memId},#{province},#{city},#{districts},#{address},#{receiver},#{phone},#{isDefault}," +
            "#{createDate},#{updateDate},#{remarks})")
    int create(Address address);

    @Results(
            id = "addressResult",
            value = {
                    @Result(property = "addressId.id", column = "id"),
                    @Result(property = "memId", column = "mem_id"),
                    @Result(property = "province", column = "province"),
                    @Result(property = "city", column = "city"),
                    @Result(property = "districts", column = "districts"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "receiver", column = "receiver"),
                    @Result(property = "phone", column = "phone"),
                    @Result(property = "isDefault", column = "is_default"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM member_address WHERE status = 1 AND is_default = 0 AND mem_id=#{memId} LIMIT 1 ")
    Address queryDefault(@Param("memId") Long memId);

    @Update("UPDATE  member_address SET province=#{province} ," +
            "city=#{city},districts=#{districts},receiver=#{receiver},address=#{address},phone=#{phone},is_default=#{isDefault}," +
            "update_date =#{updateDate},remarks =#{remarks}" +
            " WHERE id = #{addressId.id}")
    int update(Address address);

    @ResultMap("addressResult")
    @Select("SELECT * FROM member_address WHERE status=1 AND mem_id=#{memId} ORDER BY create_date DESC ")
    List<Address> findAll(@Param("memId") Long memId);

    @Update("UPDATE  member_address SET status=4 " +
            " WHERE id = #{id} ")
    int delete(@Param("id") Long id);

    @ResultMap("addressResult")
    @Select("SELECT * FROM member_address WHERE status=1 AND id=#{id} LIMIT 1 ")
    Address findById(@Param("id") Long id);


}
