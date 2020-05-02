package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.lattice.Lattice;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface LatticeMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO lattice(id,name,order_seq,is_show,shop_id,picture_url,create_date,update_date,remarks) " +
            "VALUES(#{latticeId.id},#{name},#{orderSeq},#{isShow},#{shopId},#{pictureUrl},#{createDate},#{updateDate},#{remarks})")
    int create(Lattice lattice);

    @Results(
            id = "latticeResult",
            value = {
                    @Result(property = "latticeId.id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "orderSeq", column = "order_seq"),
                    @Result(property = "isShow", column = "is_show"),
                    @Result(property = "pictureUrl", column = "picture_url"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM lattice WHERE status = 1 AND id=#{adPositionId}")
    Lattice queryById(@Param("adPositionId") Long adPositionId);

    @Update("UPDATE  lattice SET is_show=#{isShow},name=#{name},order_seq=#{orderSeq},picture_url=#{pictureUrl},shop_id=#{shopId},update_date =now(),remarks =#{remarks}" +
            " WHERE id = #{latticeId.id}")
    int update(Lattice lattice);

    @ResultMap("latticeResult")
    @Select("SELECT * FROM lattice WHERE status=1  AND shop_id=#{shopId} ORDER BY create_date DESC ")
    List<Lattice> findAll(@Param("shopId") Long shopId);

    @Update("UPDATE  lattice SET status=4 " +
            " WHERE id = #{latticeId}")
    int delete(@Param("latticeId") Long latticeId);

    @ResultMap("latticeResult")
    @Select("SELECT * FROM lattice WHERE status=1 AND is_show=0 AND shop_id=#{shopId} ORDER BY order_seq DESC ")
    List<Lattice> queryShow(@Param("shopId") Long shopId);

    @Update("UPDATE  lattice SET is_show=#{isShow} " +
            " WHERE id = #{latticeId.id}")
    int updateShow(Lattice lattice);

}
