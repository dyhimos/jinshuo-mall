package com.jinshuo.mall.service.user.application.cmd;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* app版本管理Cmd
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionCmd{
    @ApiModelProperty(value = "id")
	private Long id;	

    @ApiModelProperty(value = " 安卓下载地址")
	private String  androidDownloadAddress;

    @ApiModelProperty(value = " 苹果下载地址")
	private String  iosDownloadAddress;

    @ApiModelProperty(value = " 版本号")
    private Integer  appVersion;
}
	
