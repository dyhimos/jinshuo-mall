package com.jinshuo.mall.service.oss.mybatis.mapper;


import com.jinshuo.mall.domain.oss.OssResourceFile;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @Description: 资源上传Mapper
 * @Author: dyh
 * @CreateDate: 2019/6/19 17:50
 * @UpdateUser: dyh
 * @UpdateDate: 2019/6/19 17:50
 * @UpdateRemark:
 * @Version: 1.0
 */
@Mapper
public interface ResourceFileMapper {

    //@InsertProvider(type = ResourceFileMapper.class, method = "createSql")
    //void create(ResourceFile resourceFile);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into oss_resource_file (id, user_id, business_code, user_type, file_type, file_name, file_suffix, file_size, actual_directory, file_directory_id, file_url, contour_url, contour_act_dir," +
            " create_date, update_date, remarks, shop_id)" +
            "VALUES(#{resourceFileId.id}, #{userId}, #{businessCode}, #{userType}, #{fileType}, #{fileName}, #{fileSuffix}, #{fileSize}, #{actualDirectory}, #{fileDirectoryId}, #{fileUrl}, #{contourUrl}, #{contourActDir}," +
            "#{createDate}, #{updeDate}, #{remarks}, #{shopId})")
    void create(OssResourceFile resourceFile);


    /**
     * 创建资源记录
     *
     * @param resourceFile
     * @return
     */
    static String createSql(OssResourceFile resourceFile) {
        return new SQL()
                .INSERT_INTO("oss_resource_file")
                .INTO_COLUMNS("id", "user_id", "business_code", "user_type", "file_type", "file_name", "file_suffix", "file_size", "actual_directory", "file_directory_id", "file_url", "contour_url", "contour_act_dir",
                        "create_date", "update_date", "remarks", "shop_id")
                .INTO_VALUES("#{resourceFileId.id}", "#{userId}", "#{businessCode}", "#{userType}", "#{fileType}", "#{fileName}", "#{fileSuffix}", "#{fileSize}", "#{actualDirectory}", "#{fileDirectoryId}", "#{fileUrl}", "#{contourUrl}", "#{contourActDir}",
                        "#{createDate}", "#{updateDate}", "#{remarks}", "#{shopId}")
                .toString();
    }

    /**
     * 根据用户id 查询资源
     *
     * @param shopId
     * @return
     */
    @Results(
            id = "resourceFile",
            value = {
                    @Result(property = "resourceFileId.id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "userType", column = "user_type"),
                    @Result(property = "fileType", column = "file_type"),
                    @Result(property = "fileName", column = "file_name"),
                    @Result(property = "fileDirectoryId", column = "file_directory_id"),
                    @Result(property = "fileSuffix", column = "file_suffix"),
                    @Result(property = "fileSize", column = "file_size"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "resourceFile", column = "file_directory"),
                    @Result(property = "fileUrl", column = "file_url"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "createDate", column = "create_date")
            }
    )
    @Select("select * from oss_resource_file where shop_id = #{shopId} AND status=1 ")
    List<OssResourceFile> selectByUserId(@Param("shop_id") String shopId);


    /**
     * 更新
     *
     * @param resourceFile
     */
    @Update("update oss_resource_file set bar_codes=#{barCodes}, retail_price=#{retailPrice} where id=#{resourceFileId.id}")
    void update(OssResourceFile resourceFile);

    /**
     * 删除
     *
     * @param ids
     */
    @Update("<script>" +
            "update oss_resource_file set status=4 where id in  " +
            "<foreach collection='array' index='index' item='item' open ='(' separator=',' close=')' > " +
            "#{item}" +
            "</foreach> " +
            "</script>")
    void delete(@Param("array") String[] ids);


    /**
     * 根据目录id查询文件信息
     *
     * @param resourceFile
     */
    @ResultMap("resourceFile")
    @Select("<script>" +
            "select * from oss_resource_file where  shop_id=#{shopId} AND status=1 " +

            "<choose>" +
            "<when  test='fileDirectoryId != null '> " +
            "AND file_directory_id = #{fileDirectoryId}" +
            "</when >" +
            "<otherwise>" +
            "AND file_directory_id IS NULL " +
            "</otherwise>" +
            "</choose>" +

            "<if test='fileType != null '> " +
            "AND file_type = #{fileType}" +
            "</if>" +
            "<if test='fileName != null '> " +
            "AND (file_name like CONCAT('%',#{fileName},'%') )" +
            "</if>" +
            " ORDER BY create_date DESC " +
            "</script>")
    List<OssResourceFile> selectByDicId(OssResourceFile resourceFile);


    @ResultMap("resourceFile")
    @Select("select * from oss_resource_file where id = #{id} ")
    OssResourceFile selectById(@Param("id") Long id);

    @ResultMap("resourceFile")
    @Select("select * from oss_resource_file where file_directory_id = #{fileDirectoryId} ")
    List<OssResourceFile> selectByDId(@Param("fileDirectoryId") Long fileDirectoryId);


    /**
     * 删除
     *
     * @param fileDirectoryId
     */
    @Update("update oss_resource_file set status=4 where file_directory_id =#{fileDirectoryId}")
    int deleteByDicId(@Param("fileDirectoryId") Long fileDirectoryId);
}
