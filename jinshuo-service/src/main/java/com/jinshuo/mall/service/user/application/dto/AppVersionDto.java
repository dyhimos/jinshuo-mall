package com.jinshuo.mall.service.user.application.dto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionDto {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
	private Long id;	

    @ApiModelProperty(value = " 安卓下载地址")
	private String  androidDownloadAddress;

    @ApiModelProperty(value = " 苹果下载地址")
	private String  iosDownloadAddress;

    @ApiModelProperty(value = " app版本号")
    private Integer appVersion;

    @ApiModelProperty(value = " 0 1 2分别代表不需要，全更新，热更新")
    private Integer updateWay;

    @ApiModelProperty(value = " 热更新路径")
    private  String hotUpdatePath;
}

	
