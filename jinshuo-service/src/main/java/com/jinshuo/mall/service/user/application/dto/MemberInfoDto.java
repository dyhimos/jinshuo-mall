package com.jinshuo.mall.service.user.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员信息
 * @author dongyh
 * @Title: MemberDto
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/30 18:38
 */
@Data
@NoArgsConstructor
public class MemberInfoDto {



    @ApiModelProperty(value = "是否分销会员（1：是  2：不是）")
    private Integer isDis;

    @ApiModelProperty(value = "是否粉丝（1：是 0 :不是））")
    private Integer isFans;


    @ApiModelProperty(value = "上级用户")
    private Long pid;

    /**
     * 父名称
     */
    private String parentName;
}
