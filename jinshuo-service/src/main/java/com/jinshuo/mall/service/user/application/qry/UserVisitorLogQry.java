package com.jinshuo.mall.service.user.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVisitorLogQry {

	@ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = " 用户名称")
	private String  userName;

    @ApiModelProperty(value = " ip_addr")
	private String  ipAddr;


    @ApiModelProperty(value = " 开始时间")
	private Date starTime;

    @ApiModelProperty(value = " 结束时间")
	private Date endTime;

    @ApiModelProperty(value = " address")
	private String  address;

}

	
