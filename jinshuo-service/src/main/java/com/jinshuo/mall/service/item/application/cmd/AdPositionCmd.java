package com.jinshuo.mall.service.item.application.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 19458 on 2019/9/10.
 */
@Data
public class AdPositionCmd {
    private Long id;

    @NotNull(message = "广告位名称不能为空！")
    @ApiModelProperty(value = "广告位名称")
    private String name;

    @NotNull(message = "描述不能为空！")
    @ApiModelProperty(value = "描述")
    private String desc;

    @NotNull(message = "是否轮循不能为空！")
    @ApiModelProperty(value = "是否轮循 0是 1不是")
    private Integer isCycle; //是否轮循 0是 1不是

    //@NotNull(message = "店铺id不能为空！")
    @ApiModelProperty(value = "店铺id")
    private Long shopId; //店铺id

    //@ApiModelProperty(value = "广告集合")
    //private List<AdvertisementCmd> ads;

    @ApiModelProperty(value = "广告位代码")
    //@NotNull(message = "广告位代码不能为空！")
    private String code; //广告位代码

    @ApiModelProperty(value = "广告位状态 0正常 1停用")
    private Integer adStatus; //广告位状态

    @ApiModelProperty(value = "广告开始时间")
    private Date startTime; //广告开始时间

    @ApiModelProperty(value = "广告结束时间")
    private Date endTime; //广告结束时间

    //@NotNull(message = "展示类型不能为空！")
    @ApiModelProperty(value = "展示类型 0每次显示 1间隔显示")
    private Integer showType; //展示类型 0每次显示 1间隔时间

    @ApiModelProperty(value = "间隔时间")
    private String gapTime; //间隔时间

    @ApiModelProperty(value = "地区")
    private String areaIds; //地区

    @ApiModelProperty(value = "会员类型")
    private Integer memberType; //会员类型
}
