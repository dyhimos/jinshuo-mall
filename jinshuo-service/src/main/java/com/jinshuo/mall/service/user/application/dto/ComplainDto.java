package com.jinshuo.mall.service.user.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainDto {

    @ApiModelProperty(value = "id")
	private Long id;	

    @ApiModelProperty(value = " 举报类型")
	private String  type;

    @ApiModelProperty(value = " 帖子id")
    private Long  topicId;

    @ApiModelProperty(value = " 举报详情")
	private String  detail;

    @ApiModelProperty(value = " 举报图片路径")
    private List<String> imagePaths;
}

	
