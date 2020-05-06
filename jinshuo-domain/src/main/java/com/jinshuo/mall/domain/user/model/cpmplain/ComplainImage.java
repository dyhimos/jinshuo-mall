package com.jinshuo.mall.domain.user.model.cpmplain;

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
public class ComplainImage {
	private ComplainImageId complainImageId;
	/** 投诉id*/
	private Long  complainId;
	/** 图片路径*/
	private String  path;

	/**
	 * 图片路径
	 */
	private List<String> imagePaths;
	
}
	
