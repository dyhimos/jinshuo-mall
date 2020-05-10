package com.jinshuo.mall.service.social.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialRecommendCmd {
    @NotNull(message = "ID不能为空！")
    @ApiModelProperty(value = "ID")
    private Long aimsId;
}