package com.jinshuo.mall.service.user.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* app版本管理Cmd
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainCmd {
    @ApiModelProperty(value = "id")
	private Long id;	

    @ApiModelProperty(value = " 举报类型")
	private String  type;

    @ApiModelProperty(value = " 帖子id")
    private Long  topicId;

    @ApiModelProperty(value = " 举报详情")
	private String  detail;

    @ApiModelProperty(value = " 上传图片路径")
    private List<String> imagePaths;
}
	
