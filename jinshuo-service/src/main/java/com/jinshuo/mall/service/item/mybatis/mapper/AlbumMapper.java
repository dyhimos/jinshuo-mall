package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.album.Album;
import com.jinshuo.mall.service.item.application.qry.AlbumQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname SpuMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dyh
 */
@Mapper
public interface AlbumMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO goods_album(id,spu_id,sku_id,url,type,sort,create_date,update_date,remarks) " +
            "VALUES(#{albumId.id},#{spuId.id},#{skuId.id},#{url},#{type},#{sort},#{createDate},#{updateDate},#{remarks})")
    void create(Album album);

    @Results(
            id = "goodsalbumResult",
            value = {
                    @Result(property = "albumId.id", column = "id "),
                    @Result(property = "spuId.id", column = "spu_id"),
                    @Result(property = "skuId.id", column = "sku_id"),
                    @Result(property = "url", column = "url"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "sort", column = "sort"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date")
            }
    )
    @Select("SELECT * FROM goods_album WHERE status = '1' AND id=#{id} ")
    Album queryById(@Param("id") Long id);

    @Update("UPDATE  goods_album SET url=#{url},sort=#{sort} ," +
            "type=#{type},update_date =#{updateDate},version =#{version}" +
            " WHERE id = #{albumId.id}")
    void update(Album spu);

    @ResultMap("goodsalbumResult")
    @Select("SELECT * FROM goods_album WHERE status='1' ORDER BY sort")
    List<Album> findAll();

    @ResultMap("goodsalbumResult")
    @Select("SELECT * FROM goods_album WHERE status='1' AND sku_id=#{skuId} ORDER BY sort")
    List<Album> queryBySkuId(@Param("skuId") Long skuId);

    @ResultMap("goodsalbumResult")
    @Select("SELECT * FROM goods_album WHERE status='1' AND spu_id=#{spuId} ORDER BY sort")
    List<Album> queryBySpuId(@Param("spuId") Long spuId);


    @ResultMap("goodsalbumResult")
    @Select("<script>" +
            "SELECT * FROM goods_album WHERE status='1'" +
            "" +
            "<if test='spuId != null'> " +
            "AND spu_id = #{spuId}" +
            "</if>" +
            "<if test='skuId != null'> " +
            "AND sku_id = #{skuId}" +
            "</if>" +
            "<if test='type != null'> " +
            "AND type = #{type}" +
            "</if>" +
            "ORDER BY sort" +
            "</script>")
    List<Album> queryByExample(AlbumQry qry);

    @Update("update goods_album set status=4 where spu_id=#{spuId}")
    int deleteBySpuId(@Param("spuId") Long spuId);
}
