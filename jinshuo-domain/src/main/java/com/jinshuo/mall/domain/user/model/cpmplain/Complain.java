package com.jinshuo.mall.domain.user.model.cpmplain;

import com.jinshuo.core.model.IdentifiedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* 举报
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Complain extends IdentifiedEntity {
	private ComplainId complainId;
	/** 举报类型 1：垃圾广告、 2：不实信息 3：有害信息、4 ：其他*/
	private String  type;
	/** 举报详情*/
	private String  detail;

	/** 帖子id*/
	private Long  topicId;

	/** 用户id*/
	private Long userId;

	/**
	 * 图片路径
	 */
	private List<String> imagePaths;
	
}
	
