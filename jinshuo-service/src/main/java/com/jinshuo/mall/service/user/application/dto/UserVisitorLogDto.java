package com.jinshuo.mall.service.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVisitorLogDto {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
	private Long id;	

    @ApiModelProperty(value = " 用户名")
	private String  userName;

    @ApiModelProperty(value = " 访问ip")
	private String  ipAddr;

    @ApiModelProperty(value = " 访问次数")
	private Integer  visitCount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = " 初次访问时间")
	private Date visitFirstTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = " 最后访问时间")
	private Date  visitLastTime;

    @ApiModelProperty(value = " 访问地址")
	private String  address;
}

	
