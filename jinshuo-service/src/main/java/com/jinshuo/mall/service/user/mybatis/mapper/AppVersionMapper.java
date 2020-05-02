package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.appVersion.AppVersion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppVersionMapper {


	/**
	* 新增
	* @param appVersion
	*/
	@Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({
        "insert into app_version (",
				"id,",
				"android_download_address,",
				"ios_download_address,",
				"app_version",
				"version,",
				"status,",
				"remarks,",
				"update_date,",
				"create_date",
        ")",
        "values (",
					"#{appVersionId.id},",
					"#{androidDownloadAddress},",
					"#{iosDownloadAddress},",
					"#{appVersion},",
					"#{version},",
					"#{status.code},",
					"#{remarks},",
					"#{updateDate},",
					"#{createDate}",
        ")"
    })
    int save(AppVersion appVersion);
    
    
    
     /**
     *
     * 查询全部
     */
    @Select("<script>" +
        "select "+
				"id,"+
				"android_download_address,"+
				"ios_download_address,"+
				"app_version,"+
				"version,"+
				"status,"+
				"remarks,"+
				"update_date,"+
				"create_date"+
        " from app_version where 1=1 "+
					"<if test='androidDownloadAddress != null and androidDownloadAddress !=\"\"'> " +
	            		"and android_download_address =#{androidDownloadAddress}" +
	            	"</if>" +
					"<if test='iosDownloadAddress != null and iosDownloadAddress !=\"\"'> " +
	            		"and ios_download_address =#{iosDownloadAddress}" +
	            	"</if>" +
					"<if test='appVersion != null'> " +
					"and app_version =#{appVersion}" +
					"</if>"+
					"<if test='version != null and version !=\"\"'> " +
	            		"and version =#{version}" +
	            	"</if>" +
		         		" and status=1" +
					"<if test='remarks != null and remarks !=\"\"'> " +
	            		"and remarks =#{remarks}" +
	            	"</if>" +
					"<if test='updateDate != null and updateDate !=\"\"'> " +
	            		"and update_date =#{updateDate}" +
	            	"</if>" +
					"<if test='createDate != null and createDate !=\"\"'> " +
	            		"and create_date =#{createDate}" +
	            	"</if>" +
    "</script>")
    @Results(id ="appVersionResult",
            value = {
					@Result(column="id", property="appVersionId.id"),
					@Result(column="android_download_address", property="androidDownloadAddress"),
					@Result(column="ios_download_address", property="iosDownloadAddress"),
					@Result(column="appVersion", property="app_version"),
					@Result(column="version", property="version"),
					@Result(column="status", property="status.code"),
					@Result(column="remarks", property="remarks"),
					@Result(column="update_date", property="updateDate"),
					@Result(column="create_date", property="createDate")
    })
    List<AppVersion> selectAll(AppVersion appVersion);
    
    
    
    /**
     * 更新
     * @param appVersion
     * @return
     */
    @Update("<script>" +
            "update  app_version set " +
	            	"  android_download_address =#{androidDownloadAddress}," +
	            	"  ios_download_address =#{iosDownloadAddress}," +
					"  app_version =#{appVersion}," +
					"  version =#{version}," +
	            	"  status =#{status.code}," +
	            	"  remarks =#{remarks}," +
	            	"  update_date =#{updateDate}," +
	            	"  create_date =#{createDate}" +
            " where id= #{appVersionId.id} " +
            "</script>")
    int update(AppVersion appVersion);
    
    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    @ResultMap("appVersionResult")
    @Select("select  * from app_version  where id = #{id} limit 1")
    AppVersion findById(Long id);


	/**
	 * 查询最新的一条的app信息
	 * @return
	 */
	@ResultMap("appVersionResult")
	@Select("select  * from app_version order by create_date desc limit 1")
	AppVersion queryLatestAppVersion();

}

	
