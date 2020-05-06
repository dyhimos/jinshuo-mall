package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.cpmplain.Complain;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ComplainMapper {


	/**
	* 新增
	* @param complain
	*/
	@Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({
        "insert into complain (",
				"id,",
				"type,",
				"detail,",
				"topic_id,",
				"user_id,",
				"version,",
				"status,",
				"remarks,",
				"update_date,",
				"create_date",
        ")",
        "values (",
					"#{complainId.id},",
					"#{type},",
					"#{detail},",
					"#{topicId},",
					"#{userId},",
					"#{version},",
					"#{status.code},",
					"#{remarks},",
					"#{updateDate},",
					"#{createDate}",
        ")"
    })
    int save(Complain complain);



	/**
	 * 新增投诉图片
	 */
	@Insert({"<script>" +
			"INSERT INTO complain_image(complain_id,path) "
			+"VALUES"
			+ "<foreach  collection = 'imagePaths' item = 'imagePath'  index ='index' separator=','>"
			+ "(#{compainId},#{imagePath})"
			+ "</foreach>"
			+ "</script>"})
	int saveCompainImages(@Param("compainId") Long compainId, @Param("imagePaths") List<String> imagePaths);


	/**
	 * 删除投诉图片
	 * @param compainId
	 */
	@Delete({"<script>" +
			"delete from complain_image where complain_id=#{compainId}"
			+ "</script>"})
	void deleteCompainImages(Long compainId);

	/**
	 * 查询投诉图片列表
	 * @param compainId
	 */
	@ResultType(String.class)
	@Select({"<script>" +
			"select path from complain_image where complain_id=#{compainId}"
			+ "</script>"})
	List<String> selectCompainImages(Long compainId);

    
     /**
     *
     * 查询全部
     */
    @Select("<script>" +
        "select "+
				"id,"+
				"type,"+
				"detail,"+
				"topic_id,"+
				"user_id,"+
				"version,"+
				"status,"+
				"remarks,"+
				"update_date,"+
				"create_date"+
        " from complain where 1=1 "+
					"<if test='type != null and type !=\"\"'> " +
	            		"and type =#{type}" +
	            	"</if>" +
					"<if test='detail != null and detail !=\"\"'> " +
	            		"and detail =#{detail}" +
	            	"</if>" +
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
    @Results(id ="complainResult",
            value = {
					@Result(column="id", property="complainId.id"),
					@Result(column="type", property="type"),
					@Result(column="detail", property="detail"),
					@Result(column="topic_id", property="topicId"),
					@Result(column="user_id", property="userId"),
					@Result(column="version", property="version"),
					@Result(column="status", property="status.code"),
					@Result(column="remarks", property="remarks"),
					@Result(column="update_date", property="updateDate"),
					@Result(column="create_date", property="createDate")
    })
    List<Complain> selectAll(Complain complain);
    
    
    
    /**
     * 更新
     * @param complain
     * @return
     */
    @Update("<script>" +
            "update  complain set " +
	            	"  type =#{type}," +
	            	"  detail =#{detail}," +
	            	"  version =#{version}," +
	            	"  status =#{status.code}," +
	            	"  remarks =#{remarks}," +
	            	"  update_date =#{updateDate}," +
	            	"  create_date =#{createDate}" +
            " where id= #{complainId.id} " +
            "</script>")
    int update(Complain complain);
    
    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    @ResultMap("complainResult")
    @Select("select  * from complain  where id = #{id} limit 1")
    Complain findById(Long id);
}

	
