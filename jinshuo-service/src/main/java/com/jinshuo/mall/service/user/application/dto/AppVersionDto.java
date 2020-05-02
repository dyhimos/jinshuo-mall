package com.jinshuo.mall.service.user.application.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionDto{

    @ApiModelProperty(value = "id")
	private Long id;	

    @ApiModelProperty(value = " 安卓下载地址")
	private String  androidDownloadAddress;

    @ApiModelProperty(value = " 苹果下载地址")
	private String  iosDownloadAddress;

    @ApiModelProperty(value = " app版本号")
    private Integer appVersion;
}

	
