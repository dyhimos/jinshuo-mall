package com.jinshuo.mall.service.oss.mybatis.mapper;

import com.jinshuo.mall.domain.oss.OssFileDirectory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @Description: 目录管理Mapper
 * @Author: dyh
 * @CreateDate: 2019/6/19 17:50
 * @UpdateUser: dyh
 * @UpdateDate: 2019/6/19 17:50
 * @UpdateRemark:
 * @Version: 1.0
 */
@Mapper
public interface FileDirectoryMapper {


    static String createSql(OssFileDirectory fileDirectory) {
        return new SQL()
                .INSERT_INTO("oss_file_directory")
                .INTO_COLUMNS("id", "user_id", "file_directory_des", "directory", "father_file_directory_id", "file_type",
                        "create_date", "update_date", "remarks", "shop_id")
                .INTO_VALUES("#{fileDirectoryId.id}", "#{userId}", "#{fileDirectoryDes}", "#{directory}", "#{fatherFileDirectoryId.id}", "#{fileType}," +
                        "#{createDate}","#{updateDate}","#{remarks}","#{shopId}")
                .toString();
    }

    /*    @Options(useGeneratedKeys = true, keyProperty = "id")*/
    //@InsertProvider(type = FileDirectoryMapper.class, method = "createSql")

    @Insert("insert into oss_file_directory( id, user_id, file_directory_des, directory, father_file_directory_id, file_type,create_date, update_date, remarks, shop_id)" +
            "values(#{fileDirectoryId.id}, #{userId}, #{fileDirectoryDes}, #{directory}, #{fatherFileDirectoryId.id}, #{fileType},#{createDate},#{updateDate},#{remarks},#{shopId})")
    void create(OssFileDirectory fileDirectory);


    @Update("update oss_file_directory set file_directory_des=#{fileDirectoryDes}, directory=#{directory} where id=#{fileDirectoryId.id}")
    void updateDirecfctory(OssFileDirectory fileDirectory);

    @Update("update oss_file_directory set status=4 where  id = #{id} ")
    void deleteDirecfctory(@Param("id") String id);

    /**
     * 根据用户id 查询目录
     *
     * @param userId
     * @return List<FileDirectory>
     */
    @Results(
            id = "directoryResult",
            value = {
                    @Result(property = "fileDirectoryId.id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "fileDirectoryDes", column = "file_directory_des"),
                    @Result(property = "directory", column = "directory"),
                    @Result(property = "fileType", column = "file_type"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "fatherFileDirectoryId.id", column = "father_file_directory_id"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "status.code", column = "status"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "createDate", column = "create_date")
            }
    )
    @Select("select * from oss_file_directory where user_id = #{userId} AND status=1 AND father_file_directory_id = '' ")
    List<OssFileDirectory> selectByUserId(@Param("userId") String userId);

    @ResultMap("directoryResult")
    @Select("select * from oss_file_directory where father_file_directory_id=#{faId} AND status=1 ")
    List<OssFileDirectory> selectByFaId(@Param("faId") String faId);

    @ResultMap("directoryResult")
    @Select("<script>" +
            "select * from oss_file_directory where  shop_id = #{shopId} AND status=1 " +
            "<if test='fileType != null'> " +
            "AND file_type = #{fileType}" +
            "</if>" +
            "<choose>" +
            "<when  test='faId != null '> " +
            "AND father_file_directory_id = #{faId}" +
            "</when >" +
            "<otherwise>" +
            "AND father_file_directory_id IS NULL " +
            "</otherwise>" +
            "</choose>" +
            "</script>"
    )
    List<OssFileDirectory> selectByDto(@Param("shopId") Long shopId, @Param("userId") String userId, @Param("faId") Long faId, @Param("fileType") Integer fileType);


    @ResultMap("directoryResult")
    @Select("select * from oss_file_directory where id=#{id} AND status=1 ")
    OssFileDirectory selectById(@Param("id") Long id);

}
