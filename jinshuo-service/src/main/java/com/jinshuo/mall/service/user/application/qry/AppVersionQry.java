package com.jinshuo.mall.service.user.application.qry;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionQry {

	@ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

   

    @ApiModelProperty(value = " 安卓下载地址")
	private String  androidDownloadAddress;

    @ApiModelProperty(value = " 苹果下载地址")
	private String  iosDownloadAddress;

    @ApiModelProperty(value = " app版本号")
    private Integer  appVersion;
}

	
